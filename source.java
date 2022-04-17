
/*
* Diktya Ypologistwn I
*
* Experimental Virtual Lab
*
* Java virtual modem communications seed code
*
*/
       import java.io.*;
       import java.util.ArrayList;
	   import java.util.Scanner;
	   
	            //virtualModem
	 public class userApplication
	{
	public static void main(String[] param)
	{
		String code;
		System.out.println("Enter the request code");
		Scanner in=new Scanner(System.in);//zhteitai apo ton xrhsth na dwsei sto CMD to code_request
		code=in.nextLine();
		System.out.println("Your request code is:"+code);
    (new userApplication()).rx(code);
	
    }

	
       public void rx(String code) 
	   {//analoga me to poio gramma anixneutei sto kwdiko epilegetai h katallhlh sunarthsh
		   if (code.indexOf('E')!=-1){
			    echo(code);
		   } else if (code.indexOf('M')!=-1){
			    Image(code);
		   } else if( code.indexOf('G')!=-1){
			    ImageNoise(code);
		   } else if(code.indexOf('P')!=-1){
			    GPS(code);
		   } else if( code.indexOf('Q')!=-1){
			   System.out.println("You choose ARQ test so enter the second request code");
			   Scanner input=new Scanner(System.in);
		       String NACK=input.nextLine();
		       System.out.println("NACK result code:"+NACK);
			    echoNoise(code,NACK);
		   } else{
			   System.out.println("wrong request code,try again");
		   }
		   
	   }
         	public void echo(String code)
			{
				int k;
  File xronos_apokrishs=new File("xronos_apokrishs.txt");
  PrintWriter writer=null;
    try {
        writer = new PrintWriter (xronos_apokrishs);
        }
    catch(Exception x){
        System.out.println("No file\n");
               }
  Modem modem;
  modem=new Modem();
  modem.setSpeed(1000);
  modem.setTimeout(2000);
// Virtual modem in command mode
modem.write("atd2310ithaki\r".getBytes());
 for(;;)
{//elegxoume pote 8a stamathsei to arxiko mhnuma ths I8akhs
	k=modem.read();
	if(k!=-1)  {
	System.out.print((char)k);
	}
	if (k==-1) break;
}

// Virtual modem in data mode
  long finale=300000;
  long begin=System.currentTimeMillis();
  long check=System.currentTimeMillis();
// oso den sumplhrwnodai ta 5 lepta dexomaste paketa kai apo8hkeuoume to xrono apokrishs
      while (check-begin<finale){
         long S_time=System.currentTimeMillis();
          modem.write((code+"\r").getBytes());
       for (;;) {
         try {
              k=modem.read();
               if (k==-1) break;
                 System.out.print((char)k);
           } catch (Exception x) {
              break;
                                }
                }
     System.out.println();
  long E_time=System.currentTimeMillis();
  long D_time=S_time-E_time;
    writer.print(D_time);
   check=System.currentTimeMillis();
                                }
      writer.close();
      modem.close();
			}

      		public void Image(String code) 	
			{
				int k;
  Modem modem;
  modem=new Modem();
  modem.setSpeed(80000);
  modem.setTimeout(2000);
  // Virtual modem in command mode
  modem.write("atd2310ithaki\r".getBytes());
  modem.write((code+"\r").getBytes());
  // Virtual modem in data mode

  DataOutputStream image=null;
    try {
         image=new DataOutputStream(new FileOutputStream("E1.jpeg"));
        }
         catch(Exception x){
               System.out.println("No file");
             }
       int k_previous=0;


     for(;;){
	     try{
		      k=modem.read();
		      //System.out.print((char)k);
		        if ((k==216)&& (k_previous==255))//anazhtoume to start delemeter
		        {
			      image.writeByte((char)k_previous);
		          image.writeByte((char)k);
			         break;
		         }
	         }catch (Exception x) {
                  break;
              }
              k_previous=k;
             }
 
      for (;;) {
           try {
	          k=modem.read();
	
                image.writeByte((char)k);
                 if ((k==217) && (k_previous==255)){//anazhtoume to stop delemeter
	                image.close();
	                    break;
                                                     }
                } catch (Exception x) {
                   break;
                                        }
               k_previous=k;
              //System.out.print((char)k);
                 }
    modem.close();
			}
	    
		 public void ImageNoise(String code)
		 {
  int k;
  Modem modem;
  modem=new Modem();
  modem.setSpeed(80000);
  modem.setTimeout(2000);
  // Virtual modem in command mode
  modem.write("atd2310ithaki\r".getBytes());
  modem.write((code+"\r").getBytes());
  // Virtual modem in data mode

   DataOutputStream image=null;
     try {
         image = new DataOutputStream(new FileOutputStream("E2.jpeg"));
          }
           catch(Exception x){
            System.out.println("No file\n");
                              }
               int k_previous=0;


    for(;;){
          	try{
		        k=modem.read();
		        //System.out.print((char)k);
		           if ((k==216)&& (k_previous==255))
		            {
			           image.writeByte((char)k_previous);
			           image.writeByte((char)k);
			          break;
		            }
	            }catch (Exception x) {
                   break;
                                      }
                    k_previous=k;
            }

    for (;;) {
              try {
	                k=modem.read();
                    image.writeByte((char)k);
           if ((k==217) && (k_previous==255)){
	                image.close();
	                break;
                                                }
            } catch (Exception x) {
                      break;
                                          }
      k_previous=k;
      //System.out.print((char)k);
             }

    modem.close();
		 }
		 
		 public void GPS(String code)
		 {
			 Modem modem;     
  modem=new Modem();     
  modem.setSpeed(80000);     
  modem.setTimeout(2000);  
  // Virtual modem in command mode  
  modem.write("atd2310ithaki\r".getBytes()); 
  // Virtual modem in data mode 
 
  DataOutputStream image=null;
     try {
          image = new DataOutputStream(new FileOutputStream("M1.jpeg"));
          }
    catch(Exception x){
         System.out.println("No file\n");
      }
 
   int k=0; 
   int k_previous=0;
   int a=0;
   int a_previous=0;
   ArrayList <Character> matrixA=new ArrayList <Character>();
   StringBuilder result = new StringBuilder(matrixA.size());
   String matrixB;
   String[] parts_B;
   int B=0;
   int C=0;
   String str=null;
   int i;
   int j=0;
 
  modem.write((code+"R=1000060\r").getBytes());
     for (;;) {//diavazoume ta dedomena pou stelnei o server 
	 //otan emfanistei to 'G' exodas prohgoumenws to '$' 
	 //arxizoume na apo8hkeuoume ta dedomena se arraylist
               try {
                    k=modem.read();
                    System.out.print((char)k);
                      if (((char)k=='G') && ((char)k_previous=='$') )
                     {	
                             matrixA.add((char)k_previous);
                            matrixA.add((char)k);
	                      break;
                      }

                       } catch (Exception x) {
                          break;
                                               }
             k_previous=k;
              }
	 for(;;)
	  {
		 
                 try { 
	                 	k=modem.read();
		
		               if (k==-1) break;
		                 System.out.print((char)k);
		              	matrixA.add((char)k);
			
		                   
		          } catch (Exception x)
	                  {
                        break;  
	                     }      	 
	 }		             			  
					//metatroph tou arraylist matrixA se string matrixB
	for (Character c : matrixA) 
			{
                 result.append(c);
            }
            matrixB = result.toString();
			// "spasimo" tou string se omades pou arxizoun apo to sumvolo $ (xwris to $)
			parts_B = matrixB.split("\\$");
			
			/*int t;
			   for (t=1;t<=51;t=t+10)
			   {
				   System.out.print(parts_B[t]);
			   } 
			*/
			
		  //gia 6 paketa (ana 10 sec) apo8hkeuoume sto String str tis moires twn suntegmenwn
		  for (i=1;i<=51;i=i+10)
		  {
			  try{
				  //ousiastika metatrepoume ta minutes se minutes kai seconds
		           B=Integer.parseInt(parts_B[i].substring(22,26));
				   B=B*60;
				   B=(B/10000);
				   C=Integer.parseInt(parts_B[i].substring(35,39));
				   C=C*60;
				   C=(C/10000);			   
	             }catch (NumberFormatException nfe)
	             {
		           System.out.println("NumberFormatException: " + nfe.getMessage());
	              }
				  
				  
			 if (j==0){
				  str=parts_B[i].substring(30,34);
				  
			  j=1;
			  }else {   str=str+parts_B[i].substring(30,34);   }
		    
			str=str+Integer.toString(C);
			str=str+parts_B[i].substring(17,21);
			str=str+Integer.toString(B);
			//System.out.print("str="+str); 
		  }
	//zhtame thn epistrofh 6 stigmatwn me moires str
 modem.write(((code+"T="+str.substring(0,12)+"T="+str.substring(12,24)+"T="+str.substring(24,36)+"T="+str.substring(36,48)+"T="+str.substring(48,60)+"T="+str.substring(60,72)+"\r")).getBytes());
  //diadikasia dhmiourgias eikonas:psaxnoume gia start kai stop delemeter
        for(;;){
	           try{
	               	a=modem.read();
		            //System.out.print((char)a);
		            if ((a==216)&& (a_previous==255))
		             {
			           image.writeByte((char)a_previous);
			           image.writeByte((char)a);
			               break;
		             }
	             }catch (Exception x) {
                     break;
                   }
                          a_previous=a;
                 }
 
       for (;;) {
                try {
	                 a=modem.read();
	
                      image.writeByte((char)a);
         if ((a==217) && (a_previous==255)){
	                             image.close();
	                              break;
                   }
                    } catch (Exception x) {
                                             break;
                                           }
                      a_previous=a;
                      //System.out.print((char)a);
                }
  
        modem.close();
		 }
		 
		 public void echoNoise(String code,String NACK)
		 {
  int k;
  File xronos_apokrishs_sfalmata=new File("xronos_apokrishs_sfalmata.txt");
  PrintWriter writer=null;
    try {
          writer = new PrintWriter (xronos_apokrishs_sfalmata);
        }
          catch(Exception x){
             System.out.println("No file\n");
                             }
   Modem modem;
  modem=new Modem();
  modem.setSpeed(1000);
  modem.setTimeout(2000);
  // Virtual modem in command mode
  modem.write("atd2310ithaki\r".getBytes());
   for(;;)
   {
	  k=modem.read();
	 if (k!=-1)  {
	 System.out.print((char)k);
    }
	 if (k==-1) break;
    }

  // Virtual modem in data mode
  long finale=300000;
  long begin=System.currentTimeMillis();
  long check=System.currentTimeMillis();

   char[] msg=new char[58];
   int result=0;
   String str;
   int fcs=0;

  int i;
  int j;
  char k_previous='a';
  char k1='P';
  char k2='S';
  char kk;
  long S_time=System.currentTimeMillis();
  long E_time=0;
  long D_time=0;
  modem.write((code+"\r").getBytes());

        while (check-begin<finale){
                                   i=0;
//diavazoume dedomena kai ta apo8hkeuoume ston pinaka msg.
                   for (;;) {
                            try {
                             k=modem.read();
                             kk=(char)k;
                              System.out.print(kk);
                                   if ((kk==k2) && (k_previous==k1) )
                                   {
	                                 msg[0]=k1;
	                                 msg[1]=k2;
	                                      i=2;
	                                      break;
                                     }

                                 } catch (Exception x) {
                                            break;
                                                        }
                                k_previous=kk;
                            }

          for (;;) {
                      try {
                          k=modem.read();
                           if (k==-1) break;
                        msg[i]=(char)k;
                        System.out.print(msg[i]);
                            i++;
                        } catch (Exception x) {
                               break;
                        }
                    }
              System.out.println();
//gia sugkekrimenous xarakthres ka8e paketou upologizoume thn exor (ton enan me ton allon diadoxika)          
		  result=msg[31]^msg[32];
        for(j=33;j<=46;j++)
  {
	result=result^msg[j];
	
   }
   //System.out.print(result);
   //System.out.print("\n");

   //metatrepoume ta stoixeia tou pinaka msg se String 
   //epeita tous xarakthres pou antistoixoun sto fcs tous metatrepoume se akeraio arithmo
	str=new String(msg);
	try{
		fcs=Integer.parseInt(str.substring(49,52));
	}catch (NumberFormatException nfe)
	{
		System.out.println("NumberFormatException: " + nfe.getMessage());
	}
	//sugkrinoume to result me to fcs :an einai isa zhtame neo paketo alliws epanekpomph tou idiou.
    if (result==fcs){
		 E_time=System.currentTimeMillis();
         D_time=S_time-E_time;
        writer.print(D_time);
		
		S_time=System.currentTimeMillis();
        modem.write((code+"\r").getBytes());
		check=System.currentTimeMillis();
	} else 
	{
		check=System.currentTimeMillis();
		modem.write((NACK+"\r").getBytes());
	}

                        }

     writer.close();
     modem.close();
		 }
}	
