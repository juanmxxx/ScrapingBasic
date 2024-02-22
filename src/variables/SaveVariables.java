package variables;

public interface SaveVariables {
    public static final String DIRECTORYFILESAVE = "allfiles/";
    public static final String HTMLDIRECTORY = DIRECTORYFILESAVE + "html";
    public static final String ENCRYPTDIRECTORY = DIRECTORYFILESAVE + "cifrado";
    public static final String ENCRYPTFILENAME = ENCRYPTDIRECTORY +"/" + GeneralVariables.ACTOR + ".txt";
    public static final String HTMLFILENAME = HTMLDIRECTORY + "/" + GeneralVariables.ACTOR + ".html";


}
