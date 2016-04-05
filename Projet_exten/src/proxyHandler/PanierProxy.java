package proxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;

import plateforme.Plateforme;

public class PanierProxy implements InvocationHandler {
	private Object objet;
	
	public Object getObjet() {
		return objet;
	}

	public void setObjet(Object objet) {
		this.objet = objet;
	}

	public PanierProxy(String nomClasse, URLClassLoader urlExtLoader) {
	  Object o = null;
    try {
      o = urlExtLoader.loadClass((nomClasse.split(";")[0]).split("=")[1]).newInstance();
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      try {
        o = urlExtLoader.loadClass((nomClasse.split(";")[4]).split("=")[1]).newInstance();
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
	    objet = o;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    Object res = null;
	    res = method.invoke(objet, args);
	    if(method.getName().equals("valider")) {
	    	boolean val = (boolean)res;
	    	if(val == false) {
	    		ArrayList<String> listeExtention = Plateforme.getPlateforme().getExtensions();
	    		int i;
	    		for (i = 0; i < listeExtention.size(); i++) {
	    			if(listeExtention.get(i).contains("IPanierHandler")) {
	    			  if(listeExtention.get(i).contains("proxy=" +objet.getClass().getName())) {
	    			    this.setObjet(Plateforme.getPlateforme().creaInstance(listeExtention.get(i)));
	                    //res = this.invoke(proxy, method, args);
	                    return val;
	    			  }
	    			}
	    		}
	    		
	    	}
	    }
	    
	    return res;
	}
}
