import {describe, it} from "vitest";
// import App from "../App.tsx";
import {expect} from "vitest";
import {render, screen} from "@testing-library/react"
import TaskItem from "../TaskItem.tsx";
import type {Task} from "../TaskType.ts";

describe('Task Item', () =>{
   it('should display item', () => {
       const task1: Task = {id: 1, title: 'First Task', description: 'get task component built'};
      //Arrange
      render(<TaskItem initialTask={task1}/>)

       //expect(screen.getByText(/task/i)).toBeInTheDocument();
       expect(screen.getByRole('listitem', {name:/task/i})).toBeInTheDocument();

        expect(screen.getByText('id:1 First Task: get task component built')).toBeInTheDocument();
   });

});