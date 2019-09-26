package com.hepsiburada.test.driver;

public class SetBrowserForOS {

    public static void setDriverPath(String browser) throws Exception {

        String driverPath = "";
        String driverName = "";
        String osDriverType = "";

        switch (browser) {
            case "chrome" :
                driverName = "chrome";
                break;
            case "firefox" :
                driverName = "gecko";
                break;
            default:
                throw new Exception("__________Selected Browser Unavailable__________");
        }

        driverPath = "lib/drivers/%s/%sdriver";
        String os = FindOS.getOperationSystemName();
        System.out.println(os);
        switch (os){

            case "WINDOWS":
                osDriverType = ".exe";
                break;
            case "MAC":
                osDriverType = "mac";
                break;
            case "LINUX":
                osDriverType = "linux";
                break;
            default:
                throw new Exception("__________Operating System Is Not Code Defined__________");
        }

        driverPath = String.format(driverPath,browser,driverName) + osDriverType;

        String dir = "/" + driverPath;
        if(os.equals("WINDOWS")){

            dir = dir.replace("/","\\");
        }

        dir = System.getProperty("user.dir") + dir;

        System.out.println(dir);
        System.setProperty("webdriver." + driverName + ".driver", dir);
    }
}
