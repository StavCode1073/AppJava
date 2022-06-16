/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appjava;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author stavisc
 */
public class AppJava {

    /**
     * @param args the command line arguments
     */
    static String url = "http://localhost:8069";
    static String db = "dbodoo14";
    static String username ="stav1073@yahoo.com.mx";
    static String password = "insecto1073";
       
    public static void main(String[] args) throws Exception {

        final XmlRpcClient authClient = new XmlRpcClient();
        final XmlRpcClientConfigImpl authStartConfig = new XmlRpcClientConfigImpl();
        authStartConfig.setServerURL(
                new URL(String.format("%s/xmlrpc/2/common", url)));
        
        List configList = new ArrayList();
        Map paramMap = new HashMap();
        
        configList.add(db);
        configList.add(username);
        configList.add(password);
        configList.add(paramMap);
        
        int uid = (int)authClient.execute(
                authStartConfig, "authenticate", configList);

        final XmlRpcClient objClient = new XmlRpcClient();
        final XmlRpcClientConfigImpl objStartConfig = new XmlRpcClientConfigImpl();
        objStartConfig.setServerURL(
                new URL(String.format("%s/xmlrpc/2/object", url)));
        objClient.setConfig(objStartConfig);
        
        
        List paramList = new ArrayList();
        Map paramMapList = new HashMap();
                
        configList.clear();
        paramMap.clear();
        paramList.clear();
        
        configList.add(db);
        configList.add(uid);
        configList.add(password);
        configList.add("account.move");
        configList.add("create");
        //paramMap.put("name", "INV/2022/06/0028");
        paramMap.put("date", "2022-06-15");
        paramMap.put("move_type", "out_invoice");
        paramMap.put("partner_id", 7);
        paramMap.put("invoice_date", "2022-06-15");
        paramMap.put("currency_id", 33);
        //paramMap.put("invoice_payment_term_id", "");

        Object[] object = new Object[]{2};
        //paramMapList.put("move_name","INV/2022/06/0028");
        paramMapList.put("product_id", 2);
        paramMapList.put("product_uom_id", 1);
        paramMapList.put("quantity", 1.00);
        paramMapList.put("price_unit", 12.00);
        paramMapList.put("tax_ids", new Object[]{2});
        paramMap.put("invoice_line_ids", new Object[]{  paramMapList} );
        System.out.println(paramMapList);
        
        paramList.add(paramMap);
        configList.add(paramList);

        int cid = (int)objClient.execute("execute_kw", configList);
        
        System.out.println("Your Account Move is : " + cid);
        
        
   
    }
    
    
}
