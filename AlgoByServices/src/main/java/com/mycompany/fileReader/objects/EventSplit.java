package com.mycompany.fileReader.objects;

import java.text.ParseException;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import java.util.Iterator;
import com.mycompany.fileReader.Split.Regex;
import com.google.common.collect.Sets;
/**
 * @author Blot Elliott
 *
 */


/**
* Represent an event
*
* @param  ligne  all the event
* @param  from  get from where the event came
* @param  to  get where the event was going to
* @param  session  get the parameters from the session
* @param  label  get the label from the event
* @param  separator  get the separator of the event
* @param  params  represent all the parameters
* @param  paramsSess  represent all the parameters useful in order to use the event
* @param  date  represent the timestamp of the event
*/


public class EventSplit {
	public String ligne;
	public  String from;
	public  String to;

	private String session;
	
	private String label;
	private String separator = "|||";
	private ArrayList<String> params;
	private ArrayList<String> paramsSess;
	public String date;  //public for debug only
	public String evenement;
        public String body;
        public String status;
        public String verb;
        public String srcPort;
        public String dstPort;
        public String path;
        public boolean isJson;

	

	

	

	public EventSplit(String line, Matcher m) {
		ligne= line;
                isJson=false;
		date = m.group("date");
		label = m.group("label");
                status="";
                status=m.group("status");
		params = new ArrayList<String>();
		paramsSess = new ArrayList<String>();
                params.add(label);
                paramsSess.add(label);
		int n = 1;
                body=("");
                verb=("");
		to=("");
                srcPort=("");
                dstPort=("");
		path=("");
		//from=m.group("from");
                srcPort=m.group("srcPort");
                dstPort=m.group("dstPort");
                path=m.group("path");
                if(label.contains("JSON")){
                    isJson=true;
                }
                else{
                    body=m.group("body");
                }
                verb=m.group("verb");
		//to=m.group("to");
		try {
			while(m.group("param" + n) != null) {
				if (m.group("param" + n).contains("session=")) {
					
					session = m.group("param" + n);
				}
				
				if((!m.group("param" + n) .contains("Host=")) && (!m.group("param" + n) .contains("Dest="))){
					paramsSess.add(m.group("param" + n)); 
				}
                                params.add(m.group("param" + n));
				n++;
			
			}
                        
			
		}catch(IllegalArgumentException e) {
			//end of while
			System.out.println(e);
		}
		
	}

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getPath() {
        return path;
    }
    public boolean getIsJson() {
        return isJson;
    }
    public void setIsJson(boolean bool) {
        this.isJson=bool;
    }

    public void setPath(String path) {
        this.path = path;
    }
	
	/**
	 * Return the label of the event.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Return the parameters of the event, without session identifier.
	 */
	public ArrayList<String> getParamsWithoutFromTo() {
		return paramsSess;
	}
	
	/**
	 * Return all the parameters.
	 */
	public ArrayList<String> getparamsWithFromTo() {
		return params;
	}
	
	/**
	 * Return the source of the event.
	 */
	public String getFrom() {
		/*String res = "";
		System.out.println(paramsSess);
		for (String param:paramsSess) {
			if (param.startsWith(from + "=")){
				return param.substring(from.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;*/
		return from;
	}
        public String getTo() {
		/*String res = "";
		System.out.println(paramsSess);
		for (String param:paramsSess) {
			if (param.startsWith(from + "=")){
				return param.substring(from.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;*/
		return to;
	}
        public String getStatus() {
		/*String res = "";
		System.out.println(paramsSess);
		for (String param:paramsSess) {
			if (param.startsWith(from + "=")){
				return param.substring(from.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;*/
		return status;
	}
         public String getVerb() {
		/*String res = "";
		System.out.println(paramsSess);
		for (String param:paramsSess) {
			if (param.startsWith(from + "=")){
				return param.substring(from.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;*/
		return verb;
	}

	/**
	 * Return the destination of the event.
     * @return 
	 */
	public String  getBody() {
		/*String res = "";
		for (String param:paramsSess) {
			if (param.startsWith(to + "=")){
				return param.substring(to.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;*/
		return body;
	}

	/**
	 * Return the timestamps of the event.
	 */
	public Date getDate(Regex regex) {
		SimpleDateFormat sdf = regex.getSDF();
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			System.err.println("problem with date format");
			System.exit(3);
		}
		return d;
	}
	
	
	
	public String getligne() {
		return this.ligne;
	}

	
	public String toString() {
		String res = label + "(";
		//res = res + "date=" + date + ";";//for debug only
		for (String param: params) {
			res = res + param + separator;
		}
		res = res.substring(0, res.length()- separator.length());
		res = res +")";
		return res;
	}

	/**
	 * Print the event for debugging purposes.
	 */
	public String debug() {
		String res = label + "(";
		res = res + date;
		res = res + params.get(0);
		res = res + params.get(1);
		res = res +")\n";
		return res;
	}

	/**
	 * Check if ai has common parameters with this.
	 */
	public boolean dataSimilarity(EventSplit ai) {
		ArrayList<String> paramsi = ai.getparamsWithFromTo();
		ArrayList<String> paramsj = this.getparamsWithFromTo();
		for (String parami: paramsi) {
			if (!(parami.contains(from) || parami.contains(to))) {
				for (String paramj: paramsj) {
					if (paramj.equals(parami)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Check if the event is a request.
	 */
	public boolean isReq() {
		if (!this.toString().contains("esponse") & !this.toString().contains("Resp") & !this.toString().contains("resp") & !isInter()) {
			//System.out.println(this);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Check if the event is a response.
	 */
	public boolean isResp() {
		if (this.toString().contains("esponse") | this.toString().contains("Resp") | this.toString().contains("resp")) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Check if the event is a non-communicating action.
	 */
	public boolean isInter() {
		if (!this.toString().contains("Host=") | !this.toString().contains("Dest=")) {
			return true;
		}
		if (getFrom().equals(getTo())) {
			//System.out.println(this);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Return the session identifier.
	 */
	public String getSessionID() {
		return session;
	}

	public float heuristique(HashMap<String,List<String>> trace){
        if (trace.size()!=1){
            return 1;
        }
        else{
            return 0;
        }
    }
}

