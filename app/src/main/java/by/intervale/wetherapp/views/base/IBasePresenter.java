package by.intervale.wetherapp.views.base;

public interface IBasePresenter<T> {

    void bindView(T view);
    void unbindView();
    void handleError(Throwable exeption);
}