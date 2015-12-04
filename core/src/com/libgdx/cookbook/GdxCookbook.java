package com.libgdx.cookbook;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.libgdx.cookbook.chp02.SpriteBatchSample;
import com.libgdx.cookbook.chp02.TextureAtlasSample;

public class GdxCookbook extends Game {
	final String TAG = "Game";
	
	SpriteBatchSample sbSample;
	TextureAtlasSample textureAtlasSample;
	
	@Override
	public void create() {
		Gdx.app.log(TAG, "create");
//		sbSample = new SpriteBatchSample();
		textureAtlasSample = new TextureAtlasSample();
		setScreen(textureAtlasSample);
	}

	@Override
	public void dispose() {
		Gdx.app.log(TAG, "dispose"); // ��ִ��Game��disopose��֮��ִ��screen��hide
		super.dispose();
	}

	@Override
	public void pause() {          //  GdxCookbook����pause�¼���Ȼ�����Game��pause���ٷַ�������screen
								   //  �����screen���ʵ���˸����pause��������ô�Ͳ������Basescreen�ķ��������û��ʵ�֣�Ĭ�ϵ��ø����pause����
		Gdx.app.log(TAG, "pause"); // ��ִ��Game��Pause,֮���ٷַ�������screen��pause
		super.pause();
	}

	@Override
	public void resume() {
		Gdx.app.log(TAG, "resume");
		super.resume();
	}

	@Override
	public void render() {
		super.render();  // �̳���Game��life���̶�Ҫsuper.��ִ�У� ����basescreen�����life���̲���Ҫִ��
	}
	
}
