package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class PublicReportServiceProxy implements org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService {
  private String _endpoint = null;
  private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService publicReportService = null;
  
  public PublicReportServiceProxy() {
    _initPublicReportServiceProxy();
  }
  
  public PublicReportServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initPublicReportServiceProxy();
  }
  
  private void _initPublicReportServiceProxy() {
    try {
      publicReportService = (new org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceServiceLocator()).getPublicReportService();
      if (publicReportService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)publicReportService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)publicReportService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (publicReportService != null)
      ((javax.xml.rpc.Stub)publicReportService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService getPublicReportService() {
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService;
  }
  
  public boolean logout(java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.logout(bipSessionToken);
  }
  
  public java.lang.String impersonate(java.lang.String adminUsername, java.lang.String adminPassword, java.lang.String username) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.impersonate(adminUsername, adminPassword, username);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParameters(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getReportParameters(reportRequest, userID, password);
  }
  
  public java.lang.String createReport(java.lang.String reportName, java.lang.String folderAbsolutePathURL, java.lang.String templateFileName, byte[] templateData, java.lang.String XLIFFFileName, byte[] XLIFFData, boolean updateFlag, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.createReport(reportName, folderAbsolutePathURL, templateFileName, templateData, XLIFFFileName, XLIFFData, updateFlag, userID, password);
  }
  
  public boolean deleteReport(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteReport(reportAbsolutePath, userID, password);
  }
  
  public boolean validateLogin(java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.validateLogin(userID, password);
  }
  
  public java.lang.String login(java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.login(userID, password);
  }
  
  public java.lang.String getSecurityModel() throws java.rmi.RemoteException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getSecurityModel();
  }
  
  public int getBIPHTTPSessionInterval() throws java.rmi.RemoteException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getBIPHTTPSessionInterval();
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse runReport(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.runReport(reportRequest, userID, password);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse runReportInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.runReportInSession(reportRequest, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition getReportDefinition(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getReportDefinition(reportAbsolutePath, userID, password);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition getReportDefinitionInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getReportDefinitionInSession(reportAbsolutePath, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParametersInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getReportParametersInSession(reportRequest, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[] getFolderContents(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getFolderContents(folderAbsolutePath, userID, password);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[] getFolderContentsInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getFolderContentsInSession(folderAbsolutePath, bipSessionToken);
  }
  
  public java.lang.String scheduleReport(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest scheduleRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.scheduleReport(scheduleRequest, userID, password);
  }
  
  public java.lang.String scheduleReportInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest scheduleRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.scheduleReportInSession(scheduleRequest, bipSessionToken);
  }
  
  public boolean hasReportAccess(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.hasReportAccess(reportAbsolutePath, userID, password);
  }
  
  public boolean hasReportAccessInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.hasReportAccessInSession(reportAbsolutePath, bipSessionToken);
  }
  
  public boolean isReportExist(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.isReportExist(reportAbsolutePath, userID, password);
  }
  
  public boolean isReportExistInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.isReportExistInSession(reportAbsolutePath, bipSessionToken);
  }
  
  public boolean isFolderExist(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.isFolderExist(folderAbsolutePath, userID, password);
  }
  
  public boolean isFolderExistInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.isFolderExistInSession(folderAbsolutePath, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus getScheduledReportStatus(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportStatus(scheduledJobID, userID, password);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus getScheduledReportStatusInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportStatusInSession(scheduledJobID, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[] getListOfScheduledReportsStatus(java.lang.String[] scheduledJobIDs, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getListOfScheduledReportsStatus(scheduledJobIDs, userID, password);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[] getListOfScheduledReportsStatusInSession(java.lang.String[] scheduledJobIDs, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getListOfScheduledReportsStatusInSession(scheduledJobIDs, bipSessionToken);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[] getScheduledReportInfo(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password, java.lang.String viewByFilter) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportInfo(scheduledJobID, userID, password, viewByFilter);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[] getScheduledReportInfoInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken, java.lang.String userID, java.lang.String viewByFilter) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportInfoInSession(scheduledJobID, bipSessionToken, userID, viewByFilter);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[] getScheduledReportHistoryInfo(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password, java.lang.String viewByFilter, boolean bDownloadReport) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportHistoryInfo(scheduledJobID, userID, password, viewByFilter, bDownloadReport);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[] getScheduledReportHistoryInfoInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken, java.lang.String userID, java.lang.String viewByFilter, boolean bDownloadReport) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.getScheduledReportHistoryInfoInSession(scheduledJobID, bipSessionToken, userID, viewByFilter, bDownloadReport);
  }
  
  public boolean suspendScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.suspendScheduledReport(scheduledJobID, userID, password);
  }
  
  public boolean suspendScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.suspendScheduledReportInSession(scheduledJobID, bipSessionToken);
  }
  
  public boolean resumeScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.resumeScheduledReport(scheduledJobID, userID, password);
  }
  
  public boolean resumeScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.resumeScheduledReportInSession(scheduledJobID, bipSessionToken);
  }
  
  public boolean deleteScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteScheduledReport(scheduledJobID, userID, password);
  }
  
  public boolean deleteScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteScheduledReportInSession(scheduledJobID, bipSessionToken);
  }
  
  public boolean deleteScheduledReportHistory(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteScheduledReportHistory(scheduledJobID, userID, password);
  }
  
  public boolean deleteScheduledReportHistoryInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteScheduledReportHistoryInSession(scheduledJobID, bipSessionToken);
  }
  
  public java.lang.String createReportInSession(java.lang.String reportName, java.lang.String folderAbsolutePathURL, java.lang.String templateFileName, byte[] templateData, java.lang.String XLIFFFileName, byte[] XLIFFData, boolean updateFlag, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.createReportInSession(reportName, folderAbsolutePathURL, templateFileName, templateData, XLIFFFileName, XLIFFData, updateFlag, bipSessionToken);
  }
  
  public boolean updateReportDefinition(java.lang.String reportAbsPath, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition newReportDefn, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.updateReportDefinition(reportAbsPath, newReportDefn, userID, password);
  }
  
  public boolean updateReportDefinitionInSession(java.lang.String reportAbsPath, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition newReportDefn, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.updateReportDefinitionInSession(reportAbsPath, newReportDefn, bipSessionToken);
  }
  
  public java.lang.String createReportFolder(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.createReportFolder(folderAbsolutePath, userID, password);
  }
  
  public java.lang.String createReportFolderInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.createReportFolderInSession(folderAbsolutePath, bipSessionToken);
  }
  
  public boolean uploadTemplateForReport(java.lang.String reportAbsolutePath, java.lang.String templateFileName, byte[] templateData, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.uploadTemplateForReport(reportAbsolutePath, templateFileName, templateData, userID, password);
  }
  
  public boolean uploadTemplateForReportInSession(java.lang.String reportAbsolutePath, java.lang.String templateFileName, byte[] templateData, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.uploadTemplateForReportInSession(reportAbsolutePath, templateFileName, templateData, bipSessionToken);
  }
  
  public boolean removeTemplateForReport(java.lang.String reportAbsolutePath, java.lang.String templateFileName, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.removeTemplateForReport(reportAbsolutePath, templateFileName, userID, password);
  }
  
  public boolean removeTemplateForReportInSession(java.lang.String reportAbsolutePath, java.lang.String templateFileName, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.removeTemplateForReportInSession(reportAbsolutePath, templateFileName, bipSessionToken);
  }
  
  public java.lang.String uploadReportDataChunk(java.lang.String fileID, byte[] reportDataChunk, java.lang.String reportRawDataChunk) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.uploadReportDataChunk(fileID, reportDataChunk, reportRawDataChunk);
  }
  
  public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk downloadReportDataChunk(java.lang.String fileID, int beginIdx, int size) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.downloadReportDataChunk(fileID, beginIdx, size);
  }
  
  public java.lang.String uploadReport(java.lang.String reportName, java.lang.String folderAbsolutePathURL, byte[] reportZippedData, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.uploadReport(reportName, folderAbsolutePathURL, reportZippedData, userID, password);
  }
  
  public java.lang.String uploadReportInSession(java.lang.String reportName, java.lang.String folderAbsolutePathURL, byte[] reportZippedData, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.uploadReportInSession(reportName, folderAbsolutePathURL, reportZippedData, bipSessionToken);
  }
  
  public boolean deleteReportInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteReportInSession(reportAbsolutePath, bipSessionToken);
  }
  
  public boolean deleteFolder(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteFolder(folderAbsolutePath, userID, password);
  }
  
  public boolean deleteFolderInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.deleteFolderInSession(folderAbsolutePath, bipSessionToken);
  }
  
  public byte[] downloadReport(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.downloadReport(reportAbsolutePath, userID, password);
  }
  
  public byte[] downloadReportInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException{
    if (publicReportService == null)
      _initPublicReportServiceProxy();
    return publicReportService.downloadReportInSession(reportAbsolutePath, bipSessionToken);
  }
  
  
}