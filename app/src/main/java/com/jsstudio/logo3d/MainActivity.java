package com.jsstudio.logo3d;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.content.ContentValues;
import android.provider.MediaStore;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;
import java.io.OutputStream;
public class MainActivity extends Activity {
 @Override public void onCreate(Bundle b) {super.onCreate(b); WebView w=new WebView(this); w.getSettings().setJavaScriptEnabled(true); w.getSettings().setDomStorageEnabled(true); w.setWebChromeClient(new WebChromeClient()); w.addJavascriptInterface(new Object(){
 @JavascriptInterface public void savePng(String data){try{byte[] bytes=Base64.decode(data.substring(data.indexOf(',')+1),Base64.DEFAULT); ContentValues v=new ContentValues(); v.put(MediaStore.Images.Media.DISPLAY_NAME,"JS-Logo-"+System.currentTimeMillis()+".png"); v.put(MediaStore.Images.Media.MIME_TYPE,"image/png"); v.put(MediaStore.Images.Media.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+"/JSStudio"); android.net.Uri u=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,v); try(OutputStream o=getContentResolver().openOutputStream(u)){o.write(bytes);} runOnUiThread(()->Toast.makeText(MainActivity.this,"PNG salvo em Imagens/JSStudio",Toast.LENGTH_LONG).show());}catch(Exception e){runOnUiThread(()->Toast.makeText(MainActivity.this,"Erro ao salvar",Toast.LENGTH_LONG).show());}}
 },"Android"); setContentView(w); w.loadUrl("file:///android_asset/index.html"); }
}