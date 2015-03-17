
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DownloadResponse_QNAME = new QName("http://ftp/", "downloadResponse");
    private final static QName _GetFileListResponse_QNAME = new QName("http://ftp/", "getFileListResponse");
    private final static QName _Upload_QNAME = new QName("http://ftp/", "upload");
    private final static QName _Download_QNAME = new QName("http://ftp/", "download");
    private final static QName _LoginResponse_QNAME = new QName("http://ftp/", "loginResponse");
    private final static QName _Logoff_QNAME = new QName("http://ftp/", "logoff");
    private final static QName _LogoffResponse_QNAME = new QName("http://ftp/", "logoffResponse");
    private final static QName _GetFileList_QNAME = new QName("http://ftp/", "getFileList");
    private final static QName _Login_QNAME = new QName("http://ftp/", "login");
    private final static QName _UploadResponse_QNAME = new QName("http://ftp/", "uploadResponse");
    private final static QName _UploadArg1_QNAME = new QName("", "arg1");
    private final static QName _DownloadResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LogoffResponse }
     * 
     */
    public LogoffResponse createLogoffResponse() {
        return new LogoffResponse();
    }

    /**
     * Create an instance of {@link Download }
     * 
     */
    public Download createDownload() {
        return new Download();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Logoff }
     * 
     */
    public Logoff createLogoff() {
        return new Logoff();
    }

    /**
     * Create an instance of {@link GetFileListResponse }
     * 
     */
    public GetFileListResponse createGetFileListResponse() {
        return new GetFileListResponse();
    }

    /**
     * Create an instance of {@link Upload }
     * 
     */
    public Upload createUpload() {
        return new Upload();
    }

    /**
     * Create an instance of {@link DownloadResponse }
     * 
     */
    public DownloadResponse createDownloadResponse() {
        return new DownloadResponse();
    }

    /**
     * Create an instance of {@link UploadResponse }
     * 
     */
    public UploadResponse createUploadResponse() {
        return new UploadResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link GetFileList }
     * 
     */
    public GetFileList createGetFileList() {
        return new GetFileList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "downloadResponse")
    public JAXBElement<DownloadResponse> createDownloadResponse(DownloadResponse value) {
        return new JAXBElement<DownloadResponse>(_DownloadResponse_QNAME, DownloadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "getFileListResponse")
    public JAXBElement<GetFileListResponse> createGetFileListResponse(GetFileListResponse value) {
        return new JAXBElement<GetFileListResponse>(_GetFileListResponse_QNAME, GetFileListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "upload")
    public JAXBElement<Upload> createUpload(Upload value) {
        return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Download }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "download")
    public JAXBElement<Download> createDownload(Download value) {
        return new JAXBElement<Download>(_Download_QNAME, Download.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logoff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "logoff")
    public JAXBElement<Logoff> createLogoff(Logoff value) {
        return new JAXBElement<Logoff>(_Logoff_QNAME, Logoff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "logoffResponse")
    public JAXBElement<LogoffResponse> createLogoffResponse(LogoffResponse value) {
        return new JAXBElement<LogoffResponse>(_LogoffResponse_QNAME, LogoffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "getFileList")
    public JAXBElement<GetFileList> createGetFileList(GetFileList value) {
        return new JAXBElement<GetFileList>(_GetFileList_QNAME, GetFileList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftp/", name = "uploadResponse")
    public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
        return new JAXBElement<UploadResponse>(_UploadResponse_QNAME, UploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg1", scope = Upload.class)
    public JAXBElement<byte[]> createUploadArg1(byte[] value) {
        return new JAXBElement<byte[]>(_UploadArg1_QNAME, byte[].class, Upload.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = DownloadResponse.class)
    public JAXBElement<byte[]> createDownloadResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_DownloadResponseReturn_QNAME, byte[].class, DownloadResponse.class, ((byte[]) value));
    }

}
