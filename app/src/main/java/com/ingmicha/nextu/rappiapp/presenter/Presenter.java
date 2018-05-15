package com.ingmicha.nextu.rappiapp.presenter;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

class Presenter<T extends Presenter.View> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize(){

    }

    public void terminate(){
        dispose();
    }

    void addDisposableObserver(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    private void dispose(){
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.isDisposed();
        }
    }

    public interface View{
        Context context();
    }
}
