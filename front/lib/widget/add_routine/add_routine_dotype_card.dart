import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:todotodo/my_style.dart';

// ignore: must_be_immutable
class AddRoutineDoTypeCard extends StatefulWidget {
  AddRoutineDoTypeCard({super.key, required this.onChanged});
  Function(String) onChanged;
  @override
  State<AddRoutineDoTypeCard> createState() => _AddRoutineDoTypeCardState();
}

class _AddRoutineDoTypeCardState extends State<AddRoutineDoTypeCard> {
    String doType = 'do'; 
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: MyColors.white,borderRadius: BorderRadius.circular(16)
      ),
      child: Padding(
        padding: const EdgeInsets.fromLTRB(16, 0, 0, 0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              height: 48,
              child: Align(
                alignment: Alignment.centerLeft,
                child: Text('✅ 루틴 타입',style: MyTextStyles.h3,textAlign: TextAlign.left,))),
            SizedBox(
              height: 48,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Expanded(
                    child: TextButton(
                      onPressed: (){
                        setState(() {
                          doType = "do";
                          widget.onChanged(doType);
                        });
                      },
                      child:
                      Text('😊 건강 루틴 만들기',style: doType=='do'? MyTextStyles.h3 : MyTextStyles.h3.copyWith(color: MyColors.light_grey,)))),
                  Expanded(
                    child: TextButton(
                      onPressed: (){
                        setState(() {
                          doType = "notdo";
                          widget.onChanged(doType);
                        });
                      },
                      child: Text('😈 나쁜 습관 없애기',style: doType=='do'? MyTextStyles.h3.copyWith(color: MyColors.light_grey) : MyTextStyles.h3,))),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}