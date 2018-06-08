package com.phonegap.plugins.microblink.recognizers.serialization;

import com.microblink.entities.recognizers.Recognizer;
import com.phonegap.plugins.microblink.recognizers.RecognizerSerialization;

import org.json.JSONException;
import org.json.JSONObject;

public final class HongKongIDFrontRecognizerSerialization implements RecognizerSerialization {

    @Override
    public Recognizer<?, ?> createRecognizer(JSONObject jsonRecognizer) {
        com.microblink.entities.recognizers.blinkid.hongkong.HongKongIDFrontRecognizer recognizer = new com.microblink.entities.recognizers.blinkid.hongkong.HongKongIDFrontRecognizer();
        recognizer.setDetectGlare(jsonRecognizer.optBoolean("detectGlare", true));
        recognizer.setExtractCommercialCode(jsonRecognizer.optBoolean("extractCommercialCode", true));
        recognizer.setExtractDateOfBirth(jsonRecognizer.optBoolean("extractDateOfBirth", true));
        recognizer.setExtractDateOfIssue(jsonRecognizer.optBoolean("extractDateOfIssue", true));
        recognizer.setExtractFullName(jsonRecognizer.optBoolean("extractFullName", true));
        recognizer.setExtractSex(jsonRecognizer.optBoolean("extractSex", true));
        recognizer.setReturnFaceImage(jsonRecognizer.optBoolean("returnFaceImage", false));
        recognizer.setReturnFullDocumentImage(jsonRecognizer.optBoolean("returnFullDocumentImage", false));
        return recognizer;
    }

    @Override
    public JSONObject serializeResult(Recognizer<?, ?> recognizer) {
        com.microblink.entities.recognizers.blinkid.hongkong.HongKongIDFrontRecognizer.Result result = ((com.microblink.entities.recognizers.blinkid.hongkong.HongKongIDFrontRecognizer)recognizer).getResult();
        JSONObject jsonResult = new JSONObject();
        try {
            SerializationUtils.addCommonResultData(jsonResult, result);
            jsonResult.put("dateOfIssue", SerializationUtils.serializeDate(result.getDateOfIssue()));
            jsonResult.put("documentNumber", result.getDocumentNumber());
            jsonResult.put("faceImage", SerializationUtils.encodeImageBase64(result.getFaceImage()));
            jsonResult.put("fullDocumentImage", SerializationUtils.encodeImageBase64(result.getFullDocumentImage()));
            jsonResult.put("ownerBirthDate", SerializationUtils.serializeDate(result.getOwnerBirthDate()));
            jsonResult.put("ownerCommercialCode", result.getOwnerCommercialCode());
            jsonResult.put("ownerFullName", result.getOwnerFullName());
            jsonResult.put("ownerSex", result.getOwnerSex());
        } catch (JSONException e) {
            // see https://developer.android.com/reference/org/json/JSONException
            throw new RuntimeException(e);
        }
        return jsonResult;
    }

    @Override
    public String getJsonName() {
        return "HongKongIDFrontRecognizer";
    }

    @Override
    public Class<?> getRecognizerClass() {
        return com.microblink.entities.recognizers.blinkid.hongkong.HongKongIDFrontRecognizer.class;
    }
}