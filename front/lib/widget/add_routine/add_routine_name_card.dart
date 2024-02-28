import 'package:flutter/material.dart';
import 'package:todotodo/my_style.dart';

class AddRoutineNameCard extends StatelessWidget {
  const AddRoutineNameCard({super.key, required this.textEditingController, required this.onChanged});
  final TextEditingController textEditingController;
  final Function(String) onChanged;
  @override
  Widget build(BuildContext context) {
return TextField(
  controller:  textEditingController,
  onChanged: onChanged,
  style: MyTextStyles.h3,
  decoration: InputDecoration(    
    hintText: '투두 이름',
    hintStyle: MyTextStyles.h3.copyWith(color: MyColors.grey),
    contentPadding: EdgeInsets.all(16),
    fillColor: MyColors.white,
    filled: true,
    border: OutlineInputBorder(
      borderRadius: BorderRadius.circular(16.0),
      borderSide: BorderSide.none, // No border
    ),
  ),
);
  }
}