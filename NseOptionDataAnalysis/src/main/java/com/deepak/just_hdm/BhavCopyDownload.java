package com.deepak.just_hdm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BhavCopyDownload {
	
	public static void main(String args[]) throws Exception {
		
		String month[] = {"JUL","AUG","SEP","OCT","NOV","DEC"};
		String year[] = {"2018"};
		String day = "";
		
		for (int k =0; k<6;k++){
			
			for (int j =0;j<12;j++){
				
				for(int i= 1 ; i < 32 ; i ++){
					
					if(i<10)
						day = "0"+i;
					else
						day =i+"";
					
					String ftpUrl = "https://www.nseindia.com/content/historical/DERIVATIVES/"+ year[k] +"/"+
							month[j] +"/fo"+day+month[j]+year[k]+"bhav.csv.zip";
			        URL url = new URL(ftpUrl);
			        unpackArchive(url,year[k],month[j]);
			        System.out.println( "Finish ::" + year[k] + "::" + month[j] + "::" + day);
				}
				
			}
		}
        
    }

	
	
	public static void unpackArchive(URL url, String year , String month) throws IOException {
       /* String ftpUrl = "D:\\abc.zip";
        File zipFile = new File(ftpUrl.toString());
        ZipFile zip = new ZipFile(zipFile);*/
        
        URLConnection uc = url.openConnection();
        uc.setRequestProperty("Referer", "https://www.nseindia.com/products/content/derivatives/equities/archieve_fo.htm");
        InputStream in = null;
        
      try{
         in = new BufferedInputStream(uc.getInputStream());
      }catch (Exception fs){
    	  return;
      }
        ZipInputStream zis = new ZipInputStream(in);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println("entry: " + entry.getName() + ", "
                    + entry.getSize());
            
            String destPath = "D:\\AlgoTrade\\BhavCopy\\"+year+"\\" +month+"\\"+entry;
    //        InputStream inputStream = zis.zipFile.getInputStream(entry)
            
            
            BufferedReader bufferedeReader = new BufferedReader(
                    new InputStreamReader(zis));
            
            
            
           
            String line = bufferedeReader.readLine();
            FileOutputStream output = new FileOutputStream(destPath);
            while (line != null) {
                //System.out.println(line);
                output.write((line ).getBytes());
                output.write("\n".getBytes());
                
               line = bufferedeReader.readLine();
            }
           
            output.close();
        }
        
        zis.close();
        System.out.println(" done ");
    }
	
	
	

    
	
	
}
