package com.learnorstarve.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by markapptist on 2018-11-12.
 */

public class WinScreen extends ScreenBeta {

    TextButton toMainMenu;
    TextField textField;
    Image win;
    public HighscoreSorter highscoreSorter = MyGame.highscoreSorter;
  Sound enterSound = MyGame.enterSound;
    @Override
    public void initialize() {

        win = new Image(new Texture("UI/youwon.png"));

        toMainMenu = new TextButton("Main Menu", skin.get(("default"), TextButton.TextButtonStyle.class));
        toMainMenu.setOrigin(Align.center);
        toMainMenu.setTransform(true);
        toMainMenu.setScale(3);

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        uiStage.addActor(tableContainer);

        setUpButtons();

        textField = new TextField("", skin.get(("default"),TextField.TextFieldStyle.class));
        textField.scaleBy(3);

        win.setOrigin(Align.center);
        win.setPosition((Gdx.graphics.getWidth()/2) - win.getWidth()/2,
                (Gdx.graphics.getHeight()/2) - win.getHeight()/2);
        uiStage.addActor(win);

//        uiTable.add(win);
        uiTable.add(textField).size(textField.getPrefWidth(),textField.getPrefHeight());
        uiTable.row().padTop(HEIGHT / 2).padBottom(HEIGHT / 25);
        uiTable.add(toMainMenu).size(toMainMenu.getWidth(), toMainMenu.getHeight()).expandX();

    }

    @Override
    public void update(float dt) {

    }

    public void setUpButtons() {

        toMainMenu.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                enterSound.play();
                if (MyGame.menuScreen == null) {
                    MyGame.menuScreen = new MenuScreen();
                }
                MyGame.setActiveScreen(MyGame.menuScreen);
                highscoreSorter.checkList(MyGame.HighscoreArray,MyGame.HighscoreArray.length,101,textField.getText());
                textField.setText("");
            }
        });
    }
}
