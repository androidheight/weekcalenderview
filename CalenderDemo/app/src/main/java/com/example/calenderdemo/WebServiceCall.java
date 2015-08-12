package com.example.calenderdemo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
 
public class WebServiceCall {
	String namespace = "http://tempuri.org/";
    private String url = "http://service.examtel.com/AndroidService.asmx?";
     
    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;
     
    WebServiceCall() {
    }
 
     
    /**
     * Set Envelope
     */
    protected void SetEnvelope() {
         
        try {
             
            // Creating SOAP envelope          
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
             
            //You can comment that line if your web service is not .NET one.
            envelope.dotNet = true;
             
            envelope.setOutputSoapObject(request);
            androidHttpTransport = new AndroidHttpTransport(url);
            androidHttpTransport.debug = true;
             
        } catch (Exception e) {
            System.out.println("Soap Exception---->>>" + e.toString());   
        }
    }
 
    // MethodName variable is define for which webservice function  will call
    public String getConvertedWeight(String MethodName, String partnerid,
            String date, String studentid)
      {
         
        try {
            SOAP_ACTION = namespace + MethodName;
             
            //Adding values to request object
            request = new SoapObject(namespace, MethodName);
             
           /* //Adding Double value to request object
            PropertyInfo weightProp =new PropertyInfo();
            weightProp.setName("Weight");
            weightProp.setValue(weight);
            weightProp.setType(double.class);
            request.addProperty(weightProp);*/
            request.addProperty("partnerId", "" + partnerid);
            request.addProperty("selectedDate", "" + date);
            //Adding String value to request object
           
            request.addProperty("studentid", "" + studentid);
             
            SetEnvelope();
             
            try {
                 
                //SOAP calling webservice
                androidHttpTransport.call(SOAP_ACTION, envelope);
                 
                //Got Webservice response
                String result = envelope.getResponse().toString();
 
                return result;
                 
            } catch (Exception e) {
                // TODO: handle exception
                return e.toString();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return e.toString();
        }
 
}
}