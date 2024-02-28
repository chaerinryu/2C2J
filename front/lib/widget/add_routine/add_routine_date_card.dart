import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:todotodo/my_style.dart';

class AddRoutineDateCard extends StatefulWidget {
  AddRoutineDateCard({super.key, required this.headText, required this.onChanged});
  final String headText;
  Function(DateTime) onChanged;
  @override
  State<AddRoutineDateCard> createState() => _AddRoutineDateCardState();
}

class _AddRoutineDateCardState extends State<AddRoutineDateCard> {
  String dateText = DateTime.now().toString();
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 48,
      decoration: BoxDecoration(
        color: MyColors.white,borderRadius: BorderRadius.circular(16)
      ),
      child: Padding(
        padding: const EdgeInsets.fromLTRB(16, 0, 0, 0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text('${widget.headText}',style: MyTextStyles.h3,),
            Padding(
              padding: const EdgeInsets.all(4),
              child: GestureDetector(
                onTap: (){
                  showDatepicker(context);
                },
                child: Container(
                  width: 100,
                  decoration: BoxDecoration(
                    color: MyColors.light_grey,borderRadius: BorderRadius.circular(12)
                  ),
                  child: Center(child: Text('${dateText}',style: MyTextStyles.b3,)),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }

  void showDatepicker(BuildContext context){
    showCupertinoDialog(
                context: context, 
                barrierDismissible: true,
                builder: (BuildContext context){
                  return Align(
                    alignment: Alignment.bottomCenter,
                    child: Container(
                      color: MyColors.white,
                      height: 300,
                      child: CupertinoDatePicker(
                        mode: CupertinoDatePickerMode.date,
                        showDayOfWeek: false,
                        onDateTimeChanged: (value){
                          setState(() {
                            dateText = value.toString();
                            widget.onChanged(value);
                          });
                        },
                      ),
                    ),
                  );
                }
                );
  }
}