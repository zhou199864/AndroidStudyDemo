package cn.ich.firstcodeversiontwodemo.service;

/**
 * 定义一个接口用于对下载功能进行监听回调
 */
public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}