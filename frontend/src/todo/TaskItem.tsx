
import React from 'react';
import type {Task} from "./TaskType.ts";

type TaskProps = {
    initialTask: Task
}
//fat arrow function, if i call this function return this
export const TaskItem = ({initialTask}: TaskProps) => {

    return (

           <li aria-label="Task 1">id:{initialTask.id} {initialTask.title}: {initialTask.description}</li>

    );
};

export default TaskItem;