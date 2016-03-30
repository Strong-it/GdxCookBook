package com.libgdx.cookbook.chp02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libgdx.cookbook.help.BaseScreen;

public class TextureRegionSample extends BaseScreen {
    private final String TAG = TextureRegionSample.class.getSimpleName();

    TextureRegion region, region2, region3, region4;

    @Override
    public void show() {
        batch = new SpriteBatch();
        // ����ȫ��ͼ��
        region = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
        // ����ͼ�񣬴�����(0,0)��ʼ��ȡ��width=128, height=128֮���ͼ��,��Texture����ϵΪ׼
        region2 = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")), 128, 128);
        // ����ͼ�񣬴�����(128,128)��ʼ��ȡwidth=256, height=256֮���ͼ��,��Texture����ϵΪ׼������ĵط������벹��
        region3 = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")), 128, 128, 256, 128);
        // ����ͼ�񣬴�����(128,128)��ʼ��ȡwidth=128, height=128֮���ͼ��,��Texture����ϵΪ׼
        region4 = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")), 128, 128, 128, 128);

        Gdx.app.log(TAG, "region2 regionX=" + region2.getRegionX() + " regionY=" + region2.getRegionY());
        Gdx.app.log(TAG, "region2 width=" + region2.getRegionWidth() + " height=" + region2.getRegionHeight());

        Gdx.app.log(TAG, "region3 regionX=" + region3.getRegionX() + " regionY=" + region3.getRegionY());
        Gdx.app.log(TAG, "region3 width=" + region3.getRegionWidth() + " height=" + region3.getRegionHeight());

        Gdx.app.log(TAG, "region4 regionX=" + region4.getRegionX() + " regionY=" + region4.getRegionY());
        Gdx.app.log(TAG, "region4 width=" + region4.getRegionWidth() + " height=" + region4.getRegionHeight());
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // ��regionȫ������
        batch.draw(region, 0, 0);
        batch.draw(region2, 320, 240);
        batch.draw(region3, 0, 320, 128, 128);
        batch.draw(region4, 320, 240-128-5, 128, 128);
        batch.end();
        
        if (Gdx.input.isKeyJustPressed(Keys.B)) {
            goMainScreen();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        region.getTexture().dispose();
        region2.getTexture().dispose();
        region3.getTexture().dispose();
    }
}
