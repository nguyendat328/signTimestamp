package com.jvmfy.digitalsignature.signature;

import com.ascertia.adss.client.api.tsp.TspRequest;
import com.ascertia.adss.client.api.tsp.TspResponse;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.BaseAPIRequestDTO;
import dto.ContentRequestDTO;
import dto.DataRequestDTO;
import dto.InfoRequestDTO;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.util.encoders.Hex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Class responsible for deal with Time Stamps.
 * Time Stamp can be added when Time Stamp Authority URL is available.
 */
class TimeStampManager {
    private TSAClient tsaClient;
Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    /**
     * @param tsaUrl The url where request for Time Stamp will be done.
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    TimeStampManager(String tsaUrl) throws NoSuchAlgorithmException, MalformedURLException {
        if (tsaUrl != null) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            this.tsaClient = new TSAClient(new URL(tsaUrl), null, null, digest);
        }
    }

    /**
     * Extend cms signed data with TimeStamp first or to all signers
     *
     * @param signedData Generated CMS signed data
     * @return CMSSignedData Extended CMS signed data
     * @throws IOException
     */
    CMSSignedData addSignedTimeStamp(CMSSignedData signedData) throws IOException, TSPException {
        SignerInformationStore signerStore = signedData.getSignerInfos();
        List<SignerInformation> signersWithTimeStamp = new ArrayList<>();

        for (SignerInformation signer : signerStore.getSigners()) {
            // This adds a timestamp to every signer (into his unsigned attributes) in the signature.
            signersWithTimeStamp.add(signTimeStamp(signer));
        }

        // new SignerInformationStore have to be created cause new SignerInformation instance
        // also SignerInformationStore have to be replaced in a signedData
        return CMSSignedData.replaceSigners(signedData, new SignerInformationStore(signersWithTimeStamp));
    }

    /**
     * Extend CMS Signer Information with the TimeStampToken into the unsigned Attributes.
     *
     * @param signer information about signer
     * @return information about SignerInformation
     * @throws IOException
     */
    private SignerInformation signTimeStamp(SignerInformation signer) throws IOException, TSPException {
        AttributeTable unsignedAttributes = signer.getUnsignedAttributes();

        ASN1EncodableVector vector = new ASN1EncodableVector();
        if (unsignedAttributes != null) {
            vector = unsignedAttributes.toASN1EncodableVector();
        }



        // get timestamp token

        String hashData = "b668b4a94f350d0995d930fb544d66f6a296c563314da7f2442db480bd0acea0";
        System.out.println("=============================Hash data=============================");
        System.out.println(hashData.toUpperCase());
        System.out.println();
        String timestampToken ="";




        try {
//            Security.addProvider(new BouncyCastleProvider());
//            TspRequest obj_tspRequest = new TspRequest(hashData.getBytes());
//            obj_tspRequest.setRequestCertificate(true);
//            // Adding nonce
////            double d_random = Math.random();
////            obj_tspRequest.setNonce((long) d_random);
//            TspResponse obj_tspResponse = (TspResponse) obj_tspRequest.send( "http://10.0.20.125:8777/adss/tsa");
//            timestampToken = obj_tspResponse.getTimestampToken().getEncoded();


            URL url = new URL("https://sandbox-apim.savis.vn/tsa/1.0.0");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("X-API-KEY", "P5815683185B54C22BE7E192054A35598");
            conn.setRequestProperty("ServiceType", "102");

            // Content data
            ContentRequestDTO contentRequestDTO = new ContentRequestDTO();
            contentRequestDTO.setTransactionId("D63D76B087484B85909DCBA66DD2556A");

            DataRequestDTO dataRequestDTO = new DataRequestDTO(Hex.toHexString(signer.getSignature()), "SHA256");
            contentRequestDTO.setData(dataRequestDTO);

            // Info data
            InfoRequestDTO infoRequestDTO = new InfoRequestDTO("1.0.0", "C00001CECAMAUTES", "P10003SAVIS", 201,
                    1653293723656L, "GATEWAY2205BA0F3C3FD6E44B4C8AE4236F214511D1");
            String infoHash = Hashing.sha256().hashString(gson.toJson(infoRequestDTO), StandardCharsets.UTF_8).toString();
            String contentHash = Hashing.sha256().hashString(gson.toJson(contentRequestDTO), StandardCharsets.UTF_8).toString();
            String fullHash = infoHash.toUpperCase() + "." + contentHash.toUpperCase();

            String sign = Hashing.hmacSha256("3F9C6EC0C4CF473AB2497130B7E5BDC5".getBytes(StandardCharsets.UTF_8)).hashString(fullHash, StandardCharsets.UTF_8).toString();

            BaseAPIRequestDTO<ContentRequestDTO, InfoRequestDTO> apiRequestData = new BaseAPIRequestDTO<ContentRequestDTO, InfoRequestDTO>(
                    contentRequestDTO, infoRequestDTO,
                    sign);

            OutputStream os = conn.getOutputStream();
            String requestData = new Gson().toJson(apiRequestData);
            System.out.println();
            System.out.println("=============================Request data=============================");
            System.out.println(requestData);
            os.write(requestData.getBytes());
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println();
                System.out.println("=============================Response data=============================");
                System.out.println(output);
                JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
                timestampToken = jsonObject.get("content").getAsJsonObject().get("data").getAsJsonObject()
                        .get("timestampToken").getAsString();
                System.out.println();
                System.out.println("=============================Timestamp token=============================");
                System.out.println(timestampToken);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] token = Hex.decode(timestampToken);

       // byte[] token = this.tsaClient.getTimeStampToken(signer.getSignature());


        ASN1ObjectIdentifier oid = PKCSObjectIdentifiers.id_aa_signatureTimeStampToken;
        ASN1Encodable signatureTimeStamp = new Attribute(oid, new DERSet(ASN1Primitive.fromByteArray(token)));
        vector.add(signatureTimeStamp);
        Attributes signedAttributes = new Attributes(vector);

        // replace unsignedAttributes with the signed once
        return SignerInformation.replaceUnsignedAttributes(signer, new AttributeTable(signedAttributes));
    }
}
