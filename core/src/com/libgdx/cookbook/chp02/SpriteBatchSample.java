package com.libgdx.cookbook.chp02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.cookbook.help.BaseScreen;
import com.libgdx.cookbook.help.HelpCamera;

public class SpriteBatchSample extends BaseScreen {
	final String TAG = SpriteBatchSample.class.getSimpleName();
	
	private Texture cavemanTexture;
	
	private int width, height;
	private float originX, originY;
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		helpCamera = new HelpCamera(camera);
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
		batch = new SpriteBatch();
		
		cavemanTexture = new Texture(Gdx.files.internal("data/caveman.png"));
		cavemanTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		width = cavemanTexture.getWidth();
		height = cavemanTexture.getHeight();
		
		originX = width * 0.5f;
		originY = height * 0.5f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);  // ����batch��camera������ͶӰ
		
		batch.begin();
		batch.draw(cavemanTexture,   // texture����
					-originX, -originY,  // word space coordinates,, considering camera centered at(0, 0)
					originX, originY,   // coordinates in pixels of our texture
					width , height,     // ��������Ļ�ϻ����Ŀ�ȣ��͸߶ȣ����width / 2,��ô����Ŀ�Ȼ���룬������ȻŪ����ȫ��������
					WORLD_TO_SCREEN, WORLD_TO_SCREEN,  // scaleX, scaleY
					0f,      // rot(degress)  �Ƿ���ת
					0, 0,      // srcX, srcY  Texture�������ʼλ��
					width, height,  // Texture�Ŀ�͸�,���width / 2,��ô����ֻ�ܻ���һ��
					false, false);  //flipX, flipY  �Ƿ�X�ᷭת�� �Ƿ�Y�ᷭת
		helpCamera.operateCamera();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// �����е�resize������basescreen������
		super.resize(width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
		cavemanTexture.dispose();
	}

}
