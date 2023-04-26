package com.example.lessonpermission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lessonpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCheck.setOnClickListener {
            checkPermission()
        }

    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.tvCheck.text = "Access granted"
        } else {
            binding.tvCheck.text = "Access denied"
            requestPermission()
        }
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1){
            if (grantResults.size==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                binding.tvCheck.text=="Allowed"
            }else{
                binding.tvCheck.text=="Not allowed"
            }
        }
    }
}