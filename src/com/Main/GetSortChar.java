package com.Main;

public class GetSortChar {
	//单词码种查询
	public int getSortChar(char ch, String storage, StringBuilder token, int index){
		// TODO Auto-generated method stub
		int syn;
		switch(ch){
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
                    ch=storage.charAt(index++);  //忽略掉注释，以空格为界定
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
            token.append(ch);
            ch = storage.charAt(index++);
            if(ch == '+')
            {
            	token.append(ch);
            	syn = 53;
            	break;
            }else if(ch == '=')
            {
            	token.append(ch);
            	syn = 54;
            	break;
            }else {
            	syn = 44;
            	index--;
            }
            break;
        case '-':
            token.append(ch);
            ch = storage.charAt(index++);
            if(ch == '=') {
            	token.append(ch);
            	syn = 55;
            	break;
            }else if(ch == '-') {
            	token.append(ch);
            	syn = 56;
            	break;
            }else {
            	syn = 45;
            	index--;
            }
            break;
        case '|':
            token.append(ch);
            ch = storage.charAt(index++);
            if(ch == '|') {
            	token.append(ch);
            	syn = 58;
            	break;
            }else {
            	syn = 57;
            	index--;
            }
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
        case '\"':
        	syn = 59;
        	token.append(ch);
        	break;
        case '\'':
        	syn = 60;
        	token.append(ch);
        	break;
        case ':':
        	syn = 61;
        	token.append(ch);
        	break;
        case '%':
        	syn = 62;
        	token.append(ch);
        	break;
        case ',':
        	syn = 63;
        	token.append(ch);
        	break;
        case '&':
        	syn = 63;
        	token.append(ch);
        	break;
        case '\\':
            syn = 65;
            token.append(ch);
            break;
        case '$':        //结束符
        	syn=-3;
        	token.append(ch);
        	break;
        case '\n':       //转义符
            syn=-2;
            token.append(ch);
            break;
        case '\b':
            syn=-2;
            token.append(ch);
            break;
        case '\f':
            syn=-2;
            token.append(ch);
            break;
        case '\r':
            syn=-2;
            token.append(ch);
            break;
        case '\t':
            syn=-2;
            token.append(ch);
            break;
        default:
            syn=-1;
        }
		return syn;
	}

}
