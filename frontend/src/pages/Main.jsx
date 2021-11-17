import React from 'react';
import NavBar from '../components/NavBar/NavBar';
import {SearchProvider} from '../providers/SearchProvider';
import Footer from '../components/Footer/Footer';
import SearchBar from '../components/SearchBar/SearchBar';
import SearchList from '../components/SearchList/SearchList';
import LoginForm from "../modal/LoginForm";

const Main = () => {
    return (
        <div>
            <NavBar/>
            <SearchProvider>
                <SearchBar/>
                <SearchList/>
            </SearchProvider>
            <LoginForm/>
            <Footer/>
        </div>
    );
};

export default Main;
