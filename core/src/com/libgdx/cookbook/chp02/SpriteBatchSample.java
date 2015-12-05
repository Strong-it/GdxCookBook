package com.libgdx.cookbook.chp02;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.cookbook.help.BaseScreen;
import com.libgdx.cookbook.help.DebugTool;

public class SpriteBatchSample extends BaseScreen {
	final String TAG = SpriteBatchSample.class.getSimpleName();
	
	private Game game;
	private Texture cavemanTexture;
	private Color oldColor;
	
	private int width, height;
	private float originX, originY;
	
	public SpriteBatchSample(Game game) {
		Gdx.app.log(TAG, "constructor");
		this.game = game;
	}
	
	@Override
	public void show() {
		Gdx.app.log(TAG, "show");
		camera = new OrthographicCamera();
		debugTool = new DebugTool(camera);
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
		batch = new SpriteBatch();
		
		cavemanTexture = new Texture(Gdx.files.internal("data/caveman.png"));
		cavemanTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);  // Nearest �ȽϽӽ�ԭʼͼ��������Ӳ����
																				 // Linear �޸���Χ���ز���ģ��ͼ�� ������ë
		oldColor = new Color();
		
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
		debugTool.drawGrid(SCREEN_WIDTH, SCREEN_HEIGHT);  // ���������ȷ���������ڵ�λ��
		
		batch.begin();
		// Render caveman centered on screen
		batch.draw(cavemanTexture,   // texture����        // Texture
					-originX, -originY,  // word space coordinates,, considering camera centered at(0, 0) //  x, y
					originX, originY,   // coordinates in pixels of our texture  // originX, originY
					width , height,     // ��������Ļ�ϻ����Ŀ�ȣ��͸߶ȣ����width / 2,��ô����Ŀ�Ȼ���룬������ȻŪ����ȫ��������  // width, height
					WORLD_TO_SCREEN, WORLD_TO_SCREEN,  // scaleX, scaleY
					0f,      // rotation(degress)  �Ƿ���ת
					0, 0,      // srcX, srcY  Texture�������ʼλ��
					width, height,  // Texture�Ŀ�͸�,���width / 2,��ô����ֻ�ܻ���һ��  //scrwidth, srcHeight
					false, false);  //flipX, flipY  �Ƿ�X�ᷭת�� �Ƿ�Y�ᷭת
		
		// Render caveman on the top left corner at 2x size
		batch.draw(cavemanTexture, 
					-4.0f - originX, 1.5f -originY, // -originX, -originY ��һ��������꣬�����λ�ö�Ҫ�����2�����ϼӼ�
					originX, originY,
					width, height, 
					WORLD_TO_SCREEN * 2.0f, WORLD_TO_SCREEN * 2.0f,
					0.0f, 
					0, 0,
					width, height,
					false, false);
		
		// Render caveman on the bottom left corner at 0.5x size
		batch.draw(cavemanTexture,
				-4.0f - originX, -1.5f - originY,
				originX, originY,
				width, height,
				WORLD_TO_SCREEN * 0.5f, WORLD_TO_SCREEN * 0.5f, 
				0.0f,
				0, 0, 
				width, height, 
				false, false);
		
		// Render caveman on the top right corner at 2x size and flipped around X and Y
		batch.draw(cavemanTexture,
				4.0f - originX, 1.5f - originY,
				originX, originY,
				width, height,
				WORLD_TO_SCREEN * 2f, WORLD_TO_SCREEN * 2f, 
				0.0f,
				0, 0, 
				width, height, 
				true, true);
		
		oldColor = batch.getColor();
		
		batch.setColor(Color.RED);
		// Render red caveman
		batch.draw(cavemanTexture,
				-originX, 2.0f - originY,
				originX, originY,
				width, height,
				WORLD_TO_SCREEN, WORLD_TO_SCREEN, 
				0.0f,
				0, 0, 
				width, height, 
				false, false);
		
		batch.setColor(oldColor);
		debugTool.operateCamera();
		
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.G)) {
			gotoScreen();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// �����е�resize������basescreen������
		super.resize(width, height);
	}
	
	@Override
	public void hide() {
		Gdx.app.log(TAG, "hide");
		batch.dispose();
		cavemanTexture.dispose();
		debugTool.dispose();
	}

	private void gotoScreen() {
		game.setScreen(new TextureAtlasSample(game));
	}
}
