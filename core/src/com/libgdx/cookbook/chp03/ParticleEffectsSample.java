package com.libgdx.cookbook.chp03;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.cookbook.help.BaseScreen;

public class ParticleEffectsSample extends BaseScreen {

    private ParticleEffect[] effects;
    private ParticleEffectPool pool;
    private Array<PooledEffect> activeEffects;
    private int currentEffect;
    private Vector3 touchPos;
    
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        batch = new SpriteBatch();
        
        effects = new ParticleEffect[3];  // װ�����ӵ�����,������.particle�ļ�  ���Ҫ����dispose
        currentEffect = 0;
        touchPos = new Vector3();
        
        effects[0] = new ParticleEffect();  // �ڳ�ʼ����ʱ��
        effects[0].load(Gdx.files.internal("data/fire.particle"), Gdx.files.internal("data"));  // �ڶ����������������ڵ�Ŀ¼
        
        effects[1] = new ParticleEffect();
        effects[1].load(Gdx.files.internal("data/stars.particle"), Gdx.files.internal("data"));
        
        effects[2] = new ParticleEffect();
        effects[2].load(Gdx.files.internal("data/ice.particle"), Gdx.files.internal("data"));
        
        for (ParticleEffect particleEffect : effects) {
            particleEffect.start(); // ���ӷ�������ʼ�������� ���������startֻ����һ������ϵͳ
        }
        
        Gdx.input.setInputProcessor(this);  // ��ʵBaseScreen���Բ��̳�InputAdapter,�����ڲ�����һ�� InputAdapter inputAdapter
                                            // Gdx.input.setInputProcessor(inputAdapter) ����ʱ���ڲ���
        
        ParticleEffect explosionEffect = new ParticleEffect();
        explosionEffect.load(Gdx.files.internal("data/explosion.particle"), Gdx.files.internal("data"));
        pool = new ParticleEffectPool(explosionEffect, 10, 100);
        activeEffects = new Array<PooledEffect>();
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
//        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPos);   // screen�����world���겻�Ƕ�Ӧ�ģ�����scal��ʱ����ʱ����Ҫ����Ļ����ת����world����
        
        for (ParticleEffect particleEffect : effects) {
            particleEffect.setPosition(touchPos.x, touchPos.y);
            
            if (particleEffect.isComplete()) {
                particleEffect.reset();  // �����������ڽ������Զ����ã��������ܲ���ˢ��
            }
        }
        
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        //����ParticleEffectPool������ʱ���ε�
        effects[currentEffect].draw(batch, delta);  // ÿ��deltaʱ���ˢ��һ��
        
        
        for (int i = 0; i < activeEffects.size; ) {
            PooledEffect effect = activeEffects.get(i);
            
            if (effect.isComplete()) {
                pool.free((PooledEffect) effect);
                activeEffects.removeIndex(i);
            } else {
                effect.draw(batch, delta);
                ++i;
            }
        }
        batch.end();
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
    @Override
    public void hide() {
        batch.dispose();
        
        for (ParticleEffect particleEffect : effects) {
            particleEffect.dispose();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        currentEffect = (currentEffect + 1) % effects.length;
        
        PooledEffect effect = pool.obtain();
        
        if (effect != null) {
            touchPos.set(screenX, screenY, 0.0f);
            camera.unproject(touchPos);
            
            activeEffects.add(effect);
            effect.setPosition(touchPos.x, touchPos.y);
        }
        return true;
    }
    
}
