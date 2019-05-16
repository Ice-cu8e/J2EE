package fr.epsi.jeeProject.jmx;

public interface LevelChangeMBean {
    public String getLoggingLevel() ;
    public void setLogDebug();
    public void setLogInfo();
    public void setLogError();

}
