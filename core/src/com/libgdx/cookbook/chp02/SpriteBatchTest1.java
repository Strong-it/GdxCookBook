package com.libgdx.cookbook.chp02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libgdx.cookbook.help.BaseScreen;

public class SpriteBatchTest1 extends BaseScreen {

	private static final String TAG = SpriteBatchTest1.class.getSimpleName();
	
	Texture img;
	int width, height;
	int screenWidth, screenHeight;
	float angle = 10f;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		// ͼƬ��Դ�Ĵ�С
		width = img.getWidth();
		height = img.getHeight();
		Gdx.app.log(TAG, "Texture widht="+ width + "  height="+height);
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		Gdx.app.log(TAG, "screen width="+ screenWidth + " height="+ Gdx.graphics.getHeight());

	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		angle = (angle + 2) % 360;
		batch.begin();
		// ��Ĭ��Texture�Ĵ�С������ͼƬ
//		batch.draw(img, 0, 0);
		// ���Ч���������Ч��һ��
		batch.draw(img, 0, 0, width * 0.5f, height * 0.5f, width, height, 
				1.0f, 1.0f, 0.0f, 0, 0, width, height, false, false);
		// ����Ļ���Ŀ�ʼ����Texture��һ��������ͼ��,����൱��ͼ����С��һ��
		batch.draw(img, screenWidth * 0.5f, screenHeight * 0.5f, width *0.5f, height * 0.5f);
		// ��(350,0)���λ����Texture�Ĵ�С������ֻ��������100*100
		batch.draw(img, 350, 0, width * 0.5f, height * 0.5f, width, height, 
				1.0f, 1.0f, 0.0f, 0, 0, 100, 100, false, false);
		// ��(0,300)���λ����100*100�Ĵ�С����ͼƬ��ͬʱorigin��СҲҪ����100*0.5,ͬʱͼƬ�Թ̶��ٶ���ת
		batch.draw(img, 0, 300, 100 * 0.5f, 100 * 0.5f, 100, 100, 
					1.0f, 1.0f, angle, 0, 0, width, height, false, false);
		batch.end();
		
		 if (Gdx.input.isKeyJustPressed(Keys.B)) {
	            goMainScreen();
	        }
	}

	@Override
	public void hide() {
		// ���õ���ԴҪ���٣����ǵĹ�ͬ����implements Disposable
		batch.dispose();
		img.dispose();
	}

}
