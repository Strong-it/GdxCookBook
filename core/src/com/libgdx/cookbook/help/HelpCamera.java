package com.libgdx.cookbook.help;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class HelpCamera {
	private final String TAG = HelpCamera.class.getSimpleName();
	OrthographicCamera camera;
	Vector3 velocityCam = new Vector3(0.1f, 0.1f, 0.1f); // ��������Ĵ�СΪ12.8*7.2
															// pixel�������������ƶ��ٶ�Ҫ��С

	public HelpCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public void operateCamera() {
		if (Gdx.app.getType() != ApplicationType.Desktop) {
			return;
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.position.x += velocityCam.x; // ��������ƶ��� ���������ƶ�
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.D)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.position.x -= velocityCam.x; // ��������ƶ��� ���������ƶ�
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.S)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.position.y += velocityCam.y;
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.position.y -= velocityCam.y;
			camera.update();
		} else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.zoom += velocityCam.z; // ��С
			camera.update();
		} else if (Gdx.input.isKeyPressed(Keys.X)) {
			Gdx.app.log(TAG, "now camera positon is x=" + camera.position.x
					+ "  y=" + camera.position.y + "  z=" + camera.position.z);
			camera.zoom -= velocityCam.z; // �Ŵ�
			camera.update();
		}
	}
}
