package by.intervale.wetherapp.views.base;

import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    protected CompositeDisposable mDisposables;
    protected T mView;

    public BasePresenter() {
    }

    @Override
    public void bindView(T view) {
        this.mView = view;
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void unbindView() {
        if (mDisposables != null)
            mDisposables.dispose();
        mView = null;
    }


    @Override
    public void handleError(Throwable exception) {
        exception.printStackTrace();
    }

}
