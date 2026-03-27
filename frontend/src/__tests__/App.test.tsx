import {describe, it} from "vitest";
import App from "../App.tsx";
import {expect} from "vitest";
import {render, screen} from "@testing-library/react"
import userEvent from "@testing-library/user-event";

describe('App.tsx', () => {
    it('should display heading', () => {
        //Arrange
        render(<App/>);
        //Assert, slashes is regex and i means case insensitive
        expect(screen.getByRole('heading', {name: /started/i})).toBeInTheDocument();
        // getByRole, every element has a generic role. So here we are looking for heading role with this word.

        screen.logTestingPlaygroundURL()
        //prints out URL in the console which we can click on.
        //the page it brings us to lets us to select elements on our page and shows us what we need to type to access that specific item or element.
    });

    it('should count button increment counter', async () => {
        //Arrange
        render(<App/>)
       const user = userEvent.setup()
       const button =screen.getByRole('button', {name: /count/i});

        expect(screen.getByRole('button', {name:/0/i})).toBeVisible();

        await user.click(button);
        expect(screen.queryByRole('button', {name:/1/i})).toBeInTheDocument();

    });
});