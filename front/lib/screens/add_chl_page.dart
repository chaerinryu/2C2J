import 'package:flutter/material.dart';
import 'package:todotodo/my_style.dart';
import 'package:todotodo/widget/add_routine/add_routine_date_card.dart';
import 'package:todotodo/widget/add_routine/add_routine_dotype_card.dart';
import 'package:todotodo/widget/add_routine/add_routine_name_card.dart';
import 'package:todotodo/widget/bottom_btn.dart';
import 'package:todotodo/widget/sliver_padding_box.dart';
import 'package:todotodo/widget/sliver_spacer.dart';

// ignore: must_be_immutable
class AddCHLPage extends StatefulWidget {
  AddCHLPage({super.key});
  String chlName = "";
  DateTime startDate = DateTime.now();
  DateTime endDate = DateTime.now(); // + 1week 더하기
  String doType = "do";
  @override
  State<AddCHLPage> createState() => _pageState();
}

class _pageState extends State<AddCHLPage> {

  TextEditingController textEditingController = TextEditingController();
  void onTextChange(String value){
    setState(() {
      widget.chlName = textEditingController.text;
    });
  }

  void onTypeChange(String value){
    setState(() {
      widget.doType = value;
    });
  }

  void onStartDateChange(DateTime value){
    setState(() {
      widget.startDate = value;
    });
  }

  void onEndDateChange(DateTime value){
    setState(() {
      widget.endDate = value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: MyColors.beige,
      body: Stack(
        children: [
          CustomScrollView(
            slivers: [
              SliverAppBar(
                backgroundColor: MyColors.beige,
                leading: IconButton(
                  icon: Icon(Icons.arrow_back, color: Colors.black),
                  onPressed: () {
                    // 뒤로 가기 기능
                  },
                ),
              ),
              SliverPaddingBox(child: AddRoutineNameCard(
                textEditingController: textEditingController,
                onChanged: onTextChange,
                )
              ),
              SliverSpacer(),
              SliverPaddingBox(child: AddRoutineDateCard(
                headText: '🗓️ 시작일',
                onChanged: onStartDateChange,
              )),
              SliverSpacer(),
              SliverPaddingBox(child: AddRoutineDateCard(
                headText: '🔥 종료일',
                onChanged: onEndDateChange,
              )),
              SliverSpacer(),
              SliverPaddingBox(child: AddRoutineDoTypeCard(
                onChanged: onTypeChange,
              )),
              SliverSpacer(),
            ],
          ),
          BottomButton(
            mainColor: MyColors.sky_blue, 
            onPressed: (){
              //todo name null 체크, Snackbar 보여주기 기능
              print('todo name : ${widget.chlName}');
              print('todo date : ${widget.startDate}');
              print('todo date : ${widget.endDate}');
              print('todo type : ${widget.doType}');
            }, 
            text: '저장'
          )
        ],
      ),
    );
  }
}