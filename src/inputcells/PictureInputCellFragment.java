package inputcells;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.helloworld.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureInputCellFragment extends BaseInputCellFragment {

	final int REQUESTCODE_CAMERA=1;
	final int REQUESTCODE_ALBUM =2;
	ImageView imageView;
	TextView labelView;
	TextView hintView;
	byte[] pngData;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
View view=inflater.inflate(R.layout.fragment_inputccell_picture,container);
imageView= (ImageView) view.findViewById(R.id.image);
labelView=(TextView) view.findViewById(R.id.label);
hintView=(TextView) view.findViewById(R.id.hint);

imageView.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		onImageViewClicked();
		
	}
});

return view;
	}
	
	public void setLabelText(String labelText){
		labelView.setText(labelText);
	}
	
	public void setHintText(String hintText){
		hintView.setHint(hintText);
	}
	
	public void onImageViewClicked(){
		String[] items={
				"����",
				"���"
		};
		new AlertDialog.Builder(getActivity())
		.setTitle(labelView.getText())
		.setItems(items, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					takePhoto();
					break;
					
				case 1:
					pickFromAlbum();
					break;
				default:
					
					break;
				}
			}
		}).setNegativeButton("ȡ��", null).show();
	}
	
	
	public void takePhoto(){
		Intent itnt=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(itnt,1);
	}
	
	public void pickFromAlbum(){
		Intent itnt=new Intent(Intent.ACTION_GET_CONTENT);
		itnt.setType("image/*");
		startActivityForResult(itnt,REQUESTCODE_ALBUM);
		
	}
	
	 void saveBitmap(Bitmap bitmap) {
		// TODO Auto-generated method stub
ByteArrayOutputStream baos=new ByteArrayOutputStream();
bitmap.compress(CompressFormat.PNG,100, baos);
pngData=baos.toByteArray();
	}
	 
	public byte[] getPngData() {
		return pngData;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		if(resultCode==Activity.RESULT_CANCELED){
			return;
		}
		if(requestCode==1){
			Bitmap bmp=(Bitmap) data.getExtras().get("data");
			saveBitmap(bmp);
			imageView.setImageBitmap(bmp);
			
	//	Log.d("camera capture",data.getExtras().keySet().toString());
	//	Toast.makeText(getActivity(),data.getDataString(), Toast.LENGTH_LONG).show();
		}
		else if(requestCode==REQUESTCODE_ALBUM){
			Bitmap bmp;
			try {
				bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
				saveBitmap(bmp);
				imageView.setImageBitmap(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
}
