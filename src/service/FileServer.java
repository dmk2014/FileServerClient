
package service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FileServer", targetNamespace = "http://ftp/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FileServer {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://ftp/", className = "service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://ftp/", className = "service.LoginResponse")
    @Action(input = "http://ftp/FileServer/loginRequest", output = "http://ftp/FileServer/loginResponse")
    public boolean login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "logoff", targetNamespace = "http://ftp/", className = "service.Logoff")
    @ResponseWrapper(localName = "logoffResponse", targetNamespace = "http://ftp/", className = "service.LogoffResponse")
    @Action(input = "http://ftp/FileServer/logoffRequest", output = "http://ftp/FileServer/logoffResponse")
    public boolean logoff(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "upload", targetNamespace = "http://ftp/", className = "service.Upload")
    @ResponseWrapper(localName = "uploadResponse", targetNamespace = "http://ftp/", className = "service.UploadResponse")
    @Action(input = "http://ftp/FileServer/uploadRequest", output = "http://ftp/FileServer/uploadResponse")
    public boolean upload(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        byte[] arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "download", targetNamespace = "http://ftp/", className = "service.Download")
    @ResponseWrapper(localName = "downloadResponse", targetNamespace = "http://ftp/", className = "service.DownloadResponse")
    @Action(input = "http://ftp/FileServer/downloadRequest", output = "http://ftp/FileServer/downloadResponse")
    public byte[] download(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFileList", targetNamespace = "http://ftp/", className = "service.GetFileList")
    @ResponseWrapper(localName = "getFileListResponse", targetNamespace = "http://ftp/", className = "service.GetFileListResponse")
    @Action(input = "http://ftp/FileServer/getFileListRequest", output = "http://ftp/FileServer/getFileListResponse")
    public List<String> getFileList(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
