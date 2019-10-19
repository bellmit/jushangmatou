package com.tem.gettogether.activity.my.specificationsdetail;

import com.tem.gettogether.base.BaseView;

import java.util.Map;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specificationsdetail
 * @ClassName: SpecificationsDetailContract
 * @Author: csc
 * @CreateDate: 2019/9/9 15:36
 * @Description:
 */
public class SpecificationsDetailContract {

    interface SpecificationsDetailView extends BaseView {
        void specificationsListView();
    }

    interface Presenter {
        void addSpecifications(Map<String, Object> map);

        void deleteSpecifications(Map<String, Object> map);

        void saveSpecifications(Map<String, Object> map);
    }
}
