package com.dmraw.mealplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {

    private var _id = -1
    val id = _id

    private var _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private var _uri = MutableLiveData<String>()
    val uri : LiveData<String> = _uri

    private var _imageUri = MutableLiveData<String>()
    val imageUri : LiveData<String> = _imageUri

    private var _videoUri = MutableLiveData<String>()
    val videoUri : LiveData<String> = _videoUri

    private var _ingrediants = MutableLiveData<String>()
    val ingrediants : LiveData<String> = _ingrediants

    private var _steps = MutableLiveData<String>()
    val steps : LiveData<String> = _steps

    private var _saveModel = true
    val saveModel = _saveModel

    fun reset() {
        _id = -1
        _name.value = ""
        _uri.value = ""
        _imageUri.value = ""
        _videoUri.value = ""
        _ingrediants.value = ""
        _steps.value = ""
        _saveModel = false
    }

    fun copy() : RecipeViewModel {
        val copy = RecipeViewModel()
        copy.set(this)

        return copy
    }

    fun set(values : RecipeViewModel) {
        setId(values.id)
        setName(values.name.value?: "")
        setUri(values.uri.value?: "")
        setImageUri(values.imageUri.value?: "")
        setVideoUri(values.videoUri.value?: "")
        setIngrediants(values.ingrediants.value?: "")
        setSteps(values.steps.value?: "")
        setSaveModel(values.saveModel)
    }

    fun setId(value: Int) {
        _id = value
    }

    fun setName(value: String){
        _name.value = value
    }

    fun setUri(value: String){
        _uri.value = value
    }

    fun setImageUri(value: String){
        _imageUri.value = value
    }

    fun setVideoUri(value: String){
        _videoUri.value = value
    }

    fun setIngrediants(value: String){
        _ingrediants.value = value
    }

    fun setSteps(value: String){
        _steps.value = value
    }

    fun setSaveModel(value: Boolean){
        _saveModel = value
    }
}