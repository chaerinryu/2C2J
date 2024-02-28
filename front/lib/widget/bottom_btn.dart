import 'package:flutter/material.dart';
import 'package:todotodo/my_style.dart';

// ignore: must_be_immutable
class BottomButton extends StatelessWidget {
  BottomButton({required this.mainColor, required this.onPressed,required this.text});
  Color mainColor;
  var onPressed;
  String text;
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Align(
          alignment: Alignment.bottomCenter,
          child: Container(
            width: double.infinity,
            height: 60,
            color: mainColor,
          ),
        ),
        SafeArea(
            child: Align(
          alignment: Alignment.bottomCenter,
              child: Container(
                height: 62,
                width: double.infinity,
                  color: mainColor,
                  child: TextButton(
                      onPressed: onPressed,
                      child: Text(text,style: MyTextStyles.h3.copyWith(color: MyColors.black,),
                  )),
        ))
        ),
      ],
    );
  }
}