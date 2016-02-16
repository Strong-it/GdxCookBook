package com.libgdx.cookbook;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.libgdx.cookbook.chp02.TextureAtlasSample;
import com.libgdx.cookbook.help.SampleList;

public class GdxCookbook extends Game {
	final String TAG = "Game";
	
	String[] sampleName;
	
	@Override
	public void create() {
		Gdx.app.log(TAG, "create");
		int sampleNum = SampleList.sampleList.size();
		sampleName = new String[sampleNum];
		for (int i = 0; i < sampleNum; i++) {
			sampleName[i] = SampleList.sampleList.get(i).getSimpleName();
		}
		// TODO: �Ժ�ѧϰUI����֮����һ��List
		//Gdx.app.log(TAG, "class" + SampleList.newSample("TextureAtlasSample")); // ���ǻ�ȡһ������,������@�ַ���
		//Gdx.app.log(TAG, "class" + SampleList.sampleList.get(1));                // ���ǻ�ȡһ����
		setScreen(SampleList.newSample("LocalizationSample"));
//		setScreen(new TextureAtlasSample(this));
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
