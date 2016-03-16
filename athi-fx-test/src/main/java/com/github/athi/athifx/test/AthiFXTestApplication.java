package com.github.athi.athifx.test;

import com.github.athi.athifx.gui.AthiFXApplication;
import com.github.athi.athifx.gui.menu.group.Groups;
import com.github.athi.athifx.gui.menu.item.Items;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Athi
 */
public class AthiFXTestApplication extends AthiFXApplication<AthiFXTestApplication.TestGroup, AthiFXTestApplication.TestItem> {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void beforeLaunch() {

    }

    @Override
    public List<TestGroup> getGroups() {
        return Arrays.asList(TestGroup.values());
    }

    @Override
    public List<TestItem> getItems() {
        return Arrays.asList(TestItem.values());
    }

    enum TestGroup implements Groups {
        GROUP_ONE(1, "GROUP_ONE");

        private long id;
        private String caption;

        TestGroup(long id, String caption) {
            this.id = id;
            this.caption = caption;
        }

        public long id() {
            return id;
        }

        public String caption() {
            return caption;
        }
    }

    enum TestItem implements Items<TestGroup> {
        ITEM_ONE(1, "ITEM_ONE", TestGroup.GROUP_ONE);

        private long id;
        private String caption;
        private TestGroup group;

        TestItem(long id, String caption, TestGroup group) {
            this.id = id;
            this.caption = caption;
            this.group = group;
        }

        public long id() {
            return id;
        }

        public String caption() {
            return caption;
        }

        public TestGroup group() {
            return group;
        }
    }

}
