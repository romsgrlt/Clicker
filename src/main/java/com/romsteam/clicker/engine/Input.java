package com.romsteam.clicker.engine;

import lombok.Getter;

import java.awt.event.*;
@Getter
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private GameContainer gc;
    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] lastKeys = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] lastButtons = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;

    public Input(GameContainer gc){
        this.gc=gc;
        mouseX=0;mouseY=0;scroll=0;

        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        gc.getWindow().getCanvas().addMouseWheelListener(this);

    }

    public void update(){
        scroll = 0;
        for(int i =0; i <NUM_KEYS;++i)
            lastKeys[i]=keys[i];
        for(int i =0; i <NUM_BUTTONS;++i)
            lastButtons[i]=buttons[i];
    }
    public boolean isKey(int keyCode){
        return keys[keyCode];
    }
    public boolean isKeyUp(int keyCode){
        return !keys[keyCode]&&lastKeys[keyCode];
    }
    public boolean isKeyDown(int keyCode){
        return keys[keyCode]&&!lastKeys[keyCode];
    }
    public boolean isButton(int button){
        return buttons[button];
    }
    public boolean isButtonUp(int button){
        return !buttons[button]&&lastButtons[button];
    }
    public boolean isButtonDown(int button){
        return buttons[button]&&!lastButtons[button];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()]=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()]=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX()/gc.getScale());
        mouseY = (int) (e.getY()/gc.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX()/gc.getScale());
        mouseY = (int) (e.getY()/gc.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }
}
