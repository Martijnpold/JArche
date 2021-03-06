package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IConfirmation;

public class ServerGroupUpdateConfirmation implements IConfirmation {
    @Override
    public boolean validate() {
        return true;
    }
}
