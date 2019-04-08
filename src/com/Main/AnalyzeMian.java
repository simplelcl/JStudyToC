package com.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnalyzeMian{
    
	   //c����32������
    static String[] rwtab=new String[]{"auto","double","int","struct","break","else", 
			 "long","switch","case","register","char","extern",
			 "return","union","float","short","unsigned","continue","for",
			 "signed","void","default","goto","sizeof","volatile",
			 "do","while","static","if","typedef","enum",
			 "const"};  
    
    static String storage="";   //�洢Դ�����ַ���
    static StringBuilder token=new StringBuilder("");     //�洢����������ɵ��ַ���

    static char ch;
    static int index;
    static int syn, sum=0, row;

     //���ķ�����
    static void analyzer(){
        token.delete(0, token.length());                //�ÿ�token�������
        ch=storage.charAt(index++);
        while(ch==' '){
            ch=storage.charAt(index++);                 //ȥ���ո����
        }

        if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){     //�����ǹؼ��ֻ����Զ���ı�ʶ��
            while((ch>='0'&&ch<='9')||(ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
                token.append(ch);
                ch=storage.charAt(index++);
            }
            index--;        //�����ԭ�� 
            syn = 99;       //Ĭ��Ϊʶ������ַ���Ϊ�Զ���ı�ʶ�����ֱ���Ϊ99
            String s=token.toString();
            for(int i=0; i<rwtab.length; i++){
                if(s.equals(rwtab[i])){     
                    syn=i+1;
                    break;        //ʶ����ǹؼ���
                }
            }
        }
        else if((ch>='0'&&ch<='9')){
            sum=0;
            while((ch>='0'&&ch<='9')){
                sum=sum*10+ch-'0';        //��������
                ch=storage.charAt(index++);
            }
            index--;
            syn=100;                     //�����ͣ��ֱ���Ϊ100
        }
        else switch(ch){

        case '<':
            token.append(ch);
            ch=storage.charAt(index++);
            if(ch=='='){
                token.append(ch);
                syn=35;
            }
            else if(ch=='>'){
                token.append(ch);
                syn=34;
            }
            else{
                syn=33;
                index--;
            }
            break;
        case '>':
            token.append(ch);
            ch=storage.charAt(index++);
            if(ch=='='){
                token.append(ch);
                syn=37;
            }
            else{
                syn=36;
                index--;
            }
            break;
        case '*':
            token.append(ch);
            ch=storage.charAt(index++);
            if(ch=='*'){
                token.append(ch);
                syn=48;
            }
            else{
                syn=46;
                index--;
            }
            break;
        case '=':
            token.append(ch);
            ch=storage.charAt(index++);
            if(ch=='='){
                syn=49;
                token.append(ch);
            }
            else{
                syn=38;
                index--;
            }
            break;
        case '/':
            token.append(ch);
            ch=storage.charAt(index++);
            if(ch=='/'){
                while(ch!=' '){
                    ch=storage.charAt(index++);  //���Ե�ע�ͣ��Կո�Ϊ�綨
                }
                syn=-2;
                break;
            }else if(ch=='*') {
            	while(ch!=' ') {
            		ch = storage.charAt(index++);
            	}
            	syn = -2;
            	break;
            }else{
                syn=30;
                index--;
            }
            break;
        case '+':
            syn=44;
            token.append(ch);
            break;
        case '-':
            syn=45;
            token.append(ch);
            break;
        case ';':
            syn=41;
            token.append(ch);
            break;
        case '(':
            syn=42;
            token.append(ch);
            break;
        case ')':
            syn=43;
            token.append(ch);
            break;
        case '[':
        	syn = 39;
        	token.append(ch);
        	break;
        case ']':
        	syn = 40;
        	token.append(ch);
        	break;
        case '{':
        	syn = 51;
        	token.append(ch);
        	break;
        case '}':
        	syn = 52;
        	token.append(ch);
        	break;
        case '#':
            syn=0;
            token.append(ch);
            break;
        case '.':
        	syn = 50;
        	token.append(ch);
        	break;
        case '$':
        	syn=-3;
        	token.append(ch);
        	break;
        case '\n':
            syn=-2;
            token.append(ch);
            break;
        default:
            syn=-1;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	
    	String fileName = "D:\\c\\111.cpp";
        index=0;
        row=1;
        String tempString;
        //���ĵ��������
        try {
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
        	tempString = br.readLine();
        	while(tempString!=null)
        	{
                storage=storage+tempString+'\n';
                tempString=br.readLine();
        	};        
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}    
        
        index=0; 
        //�������
        do{
            analyzer();
            switch(syn){
            case 100:
                System.out.println("("+syn+","+sum+")");
                break;
            case -1:
                System.out.println("Error in row"+row+"!");
                break;
            case -2:
                break;
            default:
                System.out.println("("+syn+","+token+")");
            }
        }while(syn!=-3);
    }

}
