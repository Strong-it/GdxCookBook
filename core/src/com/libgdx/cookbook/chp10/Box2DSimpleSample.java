package com.libgdx.cookbook.chp10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.libgdx.cookbook.help.BaseScreen;
/**
 * shape ��״��һ��2D�ļ��ζ�������Բ �����
 * 
 * rigid body ���� һ��ʮ�ּ�Ӳ�����ʣ���������κ�����֮��ľ��붼����ȫ����ġ�
 * 
 * fixture  �о�  ����״�󶨵������ϣ�������ܶ�(density)��Ħ��(friction)���ͻָ�(restitution)�Ȳ������ԡ�
 * �о߻�����״������ײϵͳ(��ײϵͳ(Broad Phase))�У���ʹ֮����������״����ײ
 * 
 * constraint Լ�����������������ɶȵ��������ӡ�һ��2D������3�����ɶȣ�2��ƽ�������һ����ת���꣩��������ǰ�һ�����嶤��ǽ��(��ڴ�����)��
 * �����ǾͰ���Լ����ǽ�ϡ������������ֻ���������������ת��������Լ������������2�����ɶ�
 * 
 * joint �ؽ� ��һ�����ڰ�2�����߸�������̶���һ���Լ����Box2D֧�������ֹؽ����ͣ���ת ������������ȵ�
 *
 */
public class Box2DSimpleSample extends BaseScreen {
    private static final String TAG = "Box2DSimpleSample";

    // Box2D use the metric system
    private static final float SCENE_WIDTH = 12.8f; // 13 metres wide
    private static final float SCENE_HEIGHT = 7.2f; // 7 metres high

    private Vector3 point = new Vector3();

    // General Box2D
    Box2DDebugRenderer debugRenderer;  // ��Ⱦworld
    BodyDef defaultDynamicBodyDef;
    World world;   // Box2d�����������world create

    // Squares
    FixtureDef boxFixtureDef;
    PolygonShape square;  // �����

    // Circles
    CircleShape circle;  // Բ��
    FixtureDef circleFixtureDef;

    // To switch between boxes and balls
    boolean boxMode = true;

    @Override
    public void show() {
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT);
        // Center camera to get (0,0) as the origin of the Box2D world
        viewport.getCamera().position.set(viewport.getCamera().position.x
                + SCENE_WIDTH * 0.5f, viewport.getCamera().position.y
                + SCENE_HEIGHT * 0.5f, 0);
        viewport.getCamera().update();

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        // Create Physics World ��������, allow to sleep and consequently ar not simulated
        world = new World(new Vector2(0, -9.8f), true);

        // Tweak debug information
        debugRenderer = new Box2DDebugRenderer(true, /* draw bodies */
        false, /* don't draw joints */
        true, /* draw aabbs */
        true, /* draw inactive bodies */
        false, /* don't draw velocities */
        true /* draw contacts */);

        // Creates a ground to avoid objects falling forever
        createGround();

        // Default Body Definition  ��̬����,���ٶȣ����Խ������ͳ��������ã�
        defaultDynamicBodyDef = new BodyDef();
        defaultDynamicBodyDef.type = BodyType.DynamicBody;

        // Shape for square
        square = new PolygonShape();
        // 1 meter-sided square half-width = 0.5f half-height= 0.5f
        square.setAsBox(0.5f, 0.5f);

        // Shape for circles
        circle = new CircleShape();
        // 0.5 metres for radius  ���ð뾶=0.5
        circle.setRadius(0.5f);

        // Fixture definition for our shapes
        boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = square;  // ����״�󶨵�������
        boxFixtureDef.density = 0.8f;  // �ܶ�
        boxFixtureDef.friction = 0.8f;  // Ħ�� ֵ�ķ�ΧΪ 0 �� 1��Ϊ 0 ��ʾ��ȫû��Ħ����Ϊ 1 ������ζ�Ų����ڻ���
        boxFixtureDef.restitution = 0.15f;  // ���� ֵ�ķ�ΧΪ 0 �� 1, Ϊ 0 ��ʾ��ȫû�е��ԣ���ײʱ���е�������ȫ����������ȥ

        // Fixture definition for our shapes
        circleFixtureDef = new FixtureDef();
        circleFixtureDef.shape = circle;
        circleFixtureDef.density = 0.5f;
        circleFixtureDef.friction = 0.4f;
        circleFixtureDef.restitution = 0.6f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // If the game doesn't render at 60fps, the physics will go mental.
        // That'll be covered in Box2DFixedTimeStepSample
        world.step(1 / 60f, 6, 2);
//        world.step(delta, 6, 2);

        debugRenderer.render(world, viewport.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        debugRenderer.dispose();
        batch.dispose();
        square.dispose();
        circle.dispose();
        world.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {

            // Translate screen coordinates into world units
            viewport.getCamera().unproject(point.set(screenX, screenY, 0));

            if (boxMode)
                createSquare(point.x, point.y);
            else
                // Circle mode
                createCircle(point.x, point.y);
            boxMode = !boxMode;
            return true;
        }
        return false;
    }

    private void createSquare(float x, float y) {
        defaultDynamicBodyDef.position.set(x, y);

        Body body = world.createBody(defaultDynamicBodyDef);

        body.createFixture(boxFixtureDef);
    }

    private void createCircle(float x, float y) {
        defaultDynamicBodyDef.position.set(x, y);

        Body body = world.createBody(defaultDynamicBodyDef);

        body.createFixture(circleFixtureDef);
    }

    private void createGround() {

        float halfGroundWidth = SCENE_WIDTH;
        float halfGroundHeight = 0.5f; // 1 meter high

        // Create a static body definition, û���ٶȣ����������ͳ���������
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyType.StaticBody;

        // Set the ground position (origin)
        groundBodyDef.position.set(halfGroundWidth * 0.5f, halfGroundHeight);

        // Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

        // Create a rectangle shape which will fit the world_width and 1 meter
        // high
        // (setAsBox takes half-width and half-height as arguments)
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(halfGroundWidth * 0.5f, halfGroundHeight);
        // Create a fixture from our rectangle shape and add it to our ground
        // body
        groundBody.createFixture(groundBox, 0.0f);
        // Free resources
        groundBox.dispose();

    }
}
