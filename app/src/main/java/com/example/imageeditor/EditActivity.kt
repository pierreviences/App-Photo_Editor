package com.example.imageeditor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants
import com.example.imageeditor.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        val imageUri  = intent.getStringExtra("imageUri").toString()
        val dsPhotoEditorIntent = Intent(this, DsPhotoEditorActivity::class.java)

        dsPhotoEditorIntent.data = imageUri.toUri()
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Edited Image");
        startActivity(dsPhotoEditorIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode ==100){
                val outputUri:Uri? = data!!.data
                binding.editedImage.setImageURI(outputUri)
            }
        }
    }

}