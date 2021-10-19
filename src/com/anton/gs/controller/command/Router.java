package com.anton.gs.controller.command;

public class Router {
    public enum MethodType{
        FORWARD,
        REDIRECT
    }
    private String page;

    private MethodType type = MethodType.FORWARD;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public MethodType getType() {
        return type;
    }

    public void setTypeForward() {
        this.type = MethodType.FORWARD;
    }

    public void setTypeRedirect() {
        this.type = MethodType.REDIRECT;
    }
}
