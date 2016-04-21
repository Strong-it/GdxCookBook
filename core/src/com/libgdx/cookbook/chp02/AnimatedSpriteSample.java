package com.libgdx.cookbook.chp02;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.cookbook.help.BaseScreen;
import com.libgdx.cookbook.help.DebugTool;

/**
 * Rendering sprite-sheet-based animations
 *
 */
public class AnimatedSpriteSample extends BaseScreen {

    private static final float FRAME_DURATION = 1.0f / 30.0f;   // ÿ����ʾ30֡
    
    private TextureAtlas cavemanAtlas;
    private TextureAtlas dinosaurAtlas;
    private Texture background;
    
    private Animation dinosaurWalk;  // ������ģʽ��NORMAL����REVERSED ���Ե��� isAnimationFinished() 
    private Animation cavemanWalk;
    private float animationTime;  // ������ʱʱ��
    
    @Override
    public void show() {
       camera = new OrthographicCamera();
       viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
       batch = new SpriteBatch();
       debugTool = new DebugTool(camera);
       
       camera.position.set(SCENE_WIDTH * 0.5f, SCENE_HEIGHT * 0.5f, 0);  // �൱�ڰ���������������Ļ���½�,Ĭ��������Ļ�м�
       camera.update();
       animationTime = 0.0f;
       
       // load atlas and textures
       cavemanAtlas = new TextureAtlas(Gdx.files.internal("data/caveman.atlas"));
       dinosaurAtlas = new TextureAtlas(Gdx.files.internal("data/trex.atlas"));
       background = new Texture(Gdx.files.internal("data/jungle-level.png"));
       
       //load animation
       Array<AtlasRegion> cavemanRegions = new Array<AtlasRegion>(cavemanAtlas.getRegions());  // ֻ����atlsȫ�����Ƕ�����ʱ�������getRegions
       cavemanRegions.sort(new RegionComparator());
       
       Array<AtlasRegion> dinosaurRegions = new Array<AtlasRegion>(dinosaurAtlas.getRegions());
       dinosaurRegions.sort(new RegionComparator());
       
       cavemanWalk = new Animation(FRAME_DURATION, cavemanRegions, PlayMode.NORMAL);
       dinosaurWalk = new Animation(FRAME_DURATION, dinosaurRegions, PlayMode.LOOP);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // update animationTime
        animationTime += Gdx.graphics.getDeltaTime();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Render background
        int width = background.getWidth();
        int height = background.getHeight();
        
        batch.draw(background, 
                    0.0f, 0.0f,   // �����camera��������Ϊ(SCENE_WIDTH * 0.5f, SCENE_HEIGHT * 0.5f, 0) ��ô���Դ�0��0 ��ʼ
                    0.0f, 0.0f,   // ͬʱ�˴�ҲҪ����Ϊ (0, 0) 
                    width, height,
                    WORLD_TO_SCREEN, WORLD_TO_SCREEN, //����background
                    0.0f,
                    0, 0,
                    width, height,
                    false, false);
        debugTool.operateCamera();
//        debugTool.drawGrid(SCENE_WIDTH, SCENE_HEIGHT);
        
        TextureRegion cavemanFrame = cavemanWalk.getKeyFrame(animationTime);
        width = cavemanFrame.getRegionWidth();
        height = cavemanFrame.getRegionHeight();
        
        float originX = width * 0.5f;
        float originY = height * 0.5f;
        batch.draw(cavemanFrame, 
                    1.0f - originX, 3.70f - originY,
                    originX, originY,
                    width, height,
                    WORLD_TO_SCREEN, WORLD_TO_SCREEN,
                    0.0f);
        batch.draw(cavemanWalk.getKeyFrame(animationTime), 10.0f, 7.20f);
        
        TextureRegion dinosaurFrame = dinosaurWalk.getKeyFrame(animationTime);
        width = dinosaurFrame.getRegionWidth();
        height = dinosaurFrame.getRegionHeight();
        originX = width * 0.5f;
        originY = height * 0.5f;
        
        batch.draw(dinosaurFrame,
                   6.75f - originX, 4.70f - originY,
                   originX, originY,
                   width, height,
                   WORLD_TO_SCREEN, WORLD_TO_SCREEN,
                   0.0f);
        
        batch.end();
        
        goBackMainScreen();
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
    @Override
    public void hide() {
        batch.dispose();
        background.dispose();
        cavemanAtlas.dispose();
        dinosaurAtlas.dispose();
        debugTool.dispose();
    }
    
    public static class RegionComparator implements Comparator<AtlasRegion> {

        @Override
        public int compare(AtlasRegion region1, AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }
}
