package servlet.beifen_huanyuan;
import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
  
public class Command {  
    /*  
    public static void main(String[] args) throws IOException {  
        Command com = new Command();  
        com.backupDatebase("localhost","root","root", "JXC", "D:/jxc.sql");  
    } */
	
      /**  
     * 执行mysql数据库备份  
     * @param ip  
     * @param username  
     * @param password  
     * @param datebaseName  
     * @param filePath  
     * @return  
     */  
    public boolean backupDatabase(String username, String password,String databaseName, String filePath) {  
        String strCommand = "mysqldump "+" -u" + username + " -p" + password + " " + "--databases "+databaseName + " > " + filePath;  
        String result = execCmd(strCommand);  
        System.out.println("strCommand"+strCommand);  
        return true;  
    }  
      
      
      
    /**  
     * 执行dos命令  
     * @param cmd  
     * @return  
     */  
    public String execCmd(String cmd) {  
        StringBuffer sb = new StringBuffer("");  
        StringBuffer str = new StringBuffer();  
        str.append("cmd.exe /c \"").append(cmd).append("\"");  
        System.out.println("str"+str);        //打印执行的命令  
        Process ls_proc;  
        try {  
            ls_proc = Runtime.getRuntime().exec(str.toString());  
            BufferedReader in = new BufferedReader(  
                                    new InputStreamReader(  
                                        new DataInputStream(ls_proc.getInputStream())));  
            String ss = "";  
            while((ss = in.readLine()) != null) {  
                sb.append(ss).append("\n");  
            }  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
  
        return sb.toString();  
    }  
      
    
    /**  
     * 根据返回结果验证是否成功  
     * @param result  
     * @return  
     */  
    public boolean check(String result) {  
        return true;  
    }  
}  
