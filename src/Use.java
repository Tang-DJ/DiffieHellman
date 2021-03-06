import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.math.BigInteger;
import java.util.Scanner;

public class Use extends Application {
    Data data = new Data();
    DiffieHellman diffieHellman = new DiffieHellman();
    @Override
    public void start(Stage stage){

        Scene scene = come(stage);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Welcome");

    }
    /**
     * 首页面板
     * **/
    public Scene come(Stage stage){
        //主面板
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(30,12,12,12));

        //添加组件
        Label come = new Label("欢迎使用DiffieHellman算法系统");
        come.setFont(Font.font("楷体", FontWeight.BOLD, 20));
        Button one = new Button("自行输入素数及本原根");
        Button two = new Button("随机获得素数以及本原根");
        Button three = new Button("退出系统");

        //触发事件自行输入素数及本原根
        one.setOnAction(e->{
            Scene scene = choiceOne(stage);
            stage.setScene(scene);
            stage.setTitle("发送方");
            stage.show();
        });
        //触发事件随机输入素数及本原根
        two.setOnAction(e->{
            Scene scene = choiceTwo(stage);
            stage.setScene(scene);
            stage.setTitle("发送方");
            stage.show();
        });
        //退出
        three.setOnAction(e->stage.close());


        //加入面板
        VBox box = new VBox(16);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(11.5,12.5,13.5,14.5));
        box.getChildren().add(one);
        box.getChildren().add(two);
        box.getChildren().add(three);
        pane.setTop(come);
        pane.setCenter(box);
        pane.setAlignment(come,Pos.CENTER);

        Scene scene = new Scene(pane,400,250);
        return scene;
    }

    /**
     * 自行输入发送方面板
     * **/
    public Scene choiceOne(Stage stage){

        TextField sushutext =new TextField();
        TextField benyuantext = new TextField();
        TextField miyaotext = new TextField();
        Label tishi = new Label() ;
        Label miyaot = new Label();
        Label secritkeyt = new Label();
        Button queding = new Button("获得公钥");
        Button send = new Button("发送给接收方");


       queding.setOnMousePressed(e->{
           if(sushutext.getText().equals("")&&benyuantext.getText().equals("")&&miyaotext.getText().equals("")){
               tishi.setText("请输入数据");
           }
           else if(sushutext.getText().equals("")){
               tishi.setText("请输入素数");
           }
           else
               if(benyuantext.getText().equals("")){
                   int prime = Integer.valueOf(sushutext.getText());//素数
                   if (!diffieHellman.isPrime(prime)){
                       tishi.setText("该数不是素数，请输入一个素数");
                       sushutext.clear();
                   }else tishi.setText("请输入本原根");
               }

           else
               if(miyaotext.getText().equals("")){
                   int prime = Integer.valueOf(sushutext.getText());//素数
                   int benyuan = Integer.valueOf(benyuantext.getText());//本原根
                   if(!diffieHellman.isRootNumber(BigInteger.valueOf(prime),BigInteger.valueOf(benyuan))){
                       tishi.setText("该数不是素数"+prime+"的一个本原根");
                       benyuantext.clear();
                   }else tishi.setText("请输入私钥");
               }
           else {
               int prime = Integer.valueOf(sushutext.getText());//素数
               int benyuan = Integer.valueOf(benyuantext.getText());//本原根
               int miyao =Integer.valueOf(miyaotext.getText());//A的密钥

                   if (!diffieHellman.isPrime(prime)){
                       tishi.setText("该数不是素数，请输入一个素数");
                       sushutext.clear();
                   }else if(!diffieHellman.isRootNumber(BigInteger.valueOf(prime),BigInteger.valueOf(benyuan))){
                       tishi.setText("该数不是素数"+prime+"的一个本原根");
                       benyuantext.clear();
                   }else {
                       data.setP(BigInteger.valueOf(prime));
                       data.setA(BigInteger.valueOf(benyuan));
                       data.setPrivateKeyA(miyao);
                       tishi.setText("输入成功");
                       BigInteger publickey = diffieHellman.publicKey(BigInteger.valueOf(benyuan),miyao,BigInteger.valueOf(prime));
                       data.setPublicKeyA(publickey);
                       tishi.setText("发送方公钥："+publickey);
                   }
           }
        });



        send.setOnAction(e->{
            if(data.getA()==null||data.getP()==null||data.getPrivateKeyA()==0){
                tishi.setText("请输入正确数据");
            }else {
                Scene scene = getScene(stage);
                stage.setScene(scene);
                stage.setTitle("接收方");
                stage.show();
            }

        });


        GridPane gridPane = new GridPane();
        gridPane.add(new Label("素数"),0,0);
        gridPane.add(sushutext,1,0);
        gridPane.add(new Label("本原根"),0,1);
        gridPane.add(benyuantext,1,1);
        gridPane.add(new Label("发送方密钥"),0,2);
        gridPane.add(miyaotext,1,2);
        gridPane.add(queding,1,3);
        gridPane.add(send,1,4);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);
        gridPane.setHgap(6);


        HBox top = new HBox();
        top.setPadding(new Insets(15,15,15,15));
        top.getChildren().add(tishi);
        top.setAlignment(Pos.CENTER);

        HBox bottom = new HBox(15);
        bottom.setPadding(new Insets(15,15,15,15));
        bottom.getChildren().add(miyaot);
        bottom.getChildren().add(secritkeyt);
        bottom.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottom);
        Scene scene = new Scene(borderPane,400,250);

        return scene;
    }

    /**
     * 发送方面板two
     * **/
    public Scene choiceTwo(Stage stage){
        Label sushut =new Label();
        Label bent = new Label();
        Label publickeyt = new Label();
        Label secretkeyt = new Label();
        Button queding = new Button("获得公钥");
        Button send = new Button("发送给接收方");
        TextField miyaot  = new TextField();
        Label tishi = new Label();

        diffieHellman.getPandA(data);
        sushut.setText(data.getP()+"");
        bent.setText(data.getA()+"");
        queding.setOnAction(e->{
            if(!miyaot.getText().equals("")){
                tishi.setText("");
                int miyao = Integer.parseInt(miyaot.getText());
                BigInteger publickey = diffieHellman.publicKey(data.getA(),miyao,data.getP());
                publickeyt.setText("发送方公钥："+publickey);
                data.setPrivateKeyA(miyao);
                data.setPublicKeyA(publickey);
            }
            else tishi.setText("请输入数据");
            send.setOnAction(r->{
                if(!miyaot.getText().equals("")){
                    Scene scene = getScene(stage);
                    stage.setScene(scene);
                    stage.setTitle("接收方");
                    stage.show();
                }
                else tishi.setText("请输入数据");

            });
        });




        GridPane gridPane = new GridPane();
        gridPane.add(new Label("素数："),0,0);
        gridPane.add(sushut,1,0);
        gridPane.add(new Label("本原根："),0,1);
        gridPane.add(bent,1,1);
        gridPane.add(new Label("发送方私钥："),0,2);

        gridPane.add(miyaot,1,2);
        gridPane.add(queding,1,3);
        gridPane.add(send,1,4);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);
        gridPane.setHgap(6);

        HBox bottom = new HBox(15);
        bottom.setPadding(new Insets(15,15,40,15));
        bottom.getChildren().add(publickeyt);
        bottom.getChildren().add(secretkeyt);
        bottom.getChildren().add(tishi);

        bottom.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottom);


        Scene scene = new Scene(borderPane,400,250);
        return scene;
    }

    /**
     * 接受方面板
     * **/
    public Scene getScene(Stage stage){
        Button queding = new Button("确定");
        Button re = new Button("返回首页");
        Button quit = new Button("退出");
        TextField miyao  = new TextField();
        Label publickeyt = new Label();
        Text secretA = new Text();
        Text secretB = new Text();

        //触发事件
        queding.setOnAction(e->{
            if(!miyao.getText().equals("")){
                int miyaoB = Integer.parseInt(miyao.getText());

                BigInteger publickey = diffieHellman.publicKey(data.getA(),miyaoB,data.getP());
                data.setPublicKeyB(publickey);

                publickeyt.setText(publickey+"");
                data.setPrivateKeyB(miyaoB);

                BigInteger secritkeyB = diffieHellman.secretKey(data.getPublicKeyA(),data.getPrivateKeyB(),data.getP());
                BigInteger secritkeyA = diffieHellman.secretKey(data.getPublicKeyB(),data.getPrivateKeyA(),data.getP());
                data.setSecretKeyA(secritkeyA.intValue());
                data.setSecretKeyB(secritkeyB.intValue());
                secretA.setText("发送方加密密钥："+secritkeyA);
                secretB.setText("接受方加密密钥："+secritkeyB);
            }
        });
        re.setOnAction(e->{
            data.setP(null);
            data.setA(null);
            data.setPublicKeyA(null);
            data.setPublicKeyB(null);
            Scene scene = come(stage);
            stage.setScene(scene);
            stage.setTitle("Welcome");
            stage.show();
        });
        quit.setOnAction(e->stage.close());

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("发送方公钥："),0,0);
        gridPane.add(new Label(data.getPublicKeyA()+""),1,0);
        gridPane.add(new Label("素数："),0,1);
        gridPane.add(new Label(data.getP()+""),1,1);
        gridPane.add(new Label("本原根："),0,2);
        gridPane.add(new Label(data.getA()+""),1,2);
        gridPane.add(new Label("接收方私钥："),0,3);
        gridPane.add(miyao,1,3);
        gridPane.add(queding,2,4);
        gridPane.add(new Label("接收方公钥："),0,5);
        gridPane.add(publickeyt,1,5);

        gridPane.add(secretA,0,6);
        gridPane.add(secretB,0,7);


        gridPane.add(re,2,7);
        gridPane.add(quit,2,6);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);
        gridPane.setHgap(4);

        Scene scene = new Scene(gridPane,400,250);
        return scene;
    }

}


