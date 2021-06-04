import Vue from 'vue'
import Vuex from 'vuex'
import { searchSampleList, deleteSampleById } from '@/api/sample'
import { sleep } from '@/utils/schedule'
import { message } from 'ant-design-vue'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        // loading
        dataLoading: false,
        formSearchLoading: false,
        // data
        sampleList: [],
        total: 0,
        pageIndex: 1,
        pageSize: 10,
        // modal
        modal: {
            visible: false,
            confirmLoading: false,
            // data
            id: undefined,
            name: undefined,
            type: undefined,
        },
        // search
        searchParams: {
            name: undefined,
            type: undefined,
        }
    },
    mutations: {
        setFormSearchLoading(state, payload) {
            state.formSearchLoading = payload
        },
        setModalData(state, payload = {}) {
            state.modal = {
                ...state.modal,
                ...payload,
            }
        },
        setDataLoading(state, payload) {
            state.dataLoading = payload
        },
        setSampleList(state, payload) {
            state.sampleList = payload
        },
        setSearchParams(state, payload = {}) {
            state.searchParams = {
                ...state.searchParams,
                ...payload,
            }
        },
        setTotal(state, payload) {
            state.total = payload
        },
        setPageIndex(state, payload) {
            state.pageIndex = payload
        },
        setPageSize(state, payload) {
            state.pageSize = payload
        }
    },
    actions: {
        async initialLoadPageData({ state, commit, dispatch }, payload) {
            await dispatch('searchSampleList')
        },
        async searchSampleList({ state, commit, dispatch }, payload) {
            commit('setDataLoading', true)
            const result = await searchSampleList({
                ...state.searchParams,
                pageIndex: state.pageIndex,
                pageSize: state.pageSize
            })
            commit('setSampleList', result.list)
            commit('setTotal', result.total)
            commit('setDataLoading', false)
        },
        async saveSample({ state, commit, dispatch }, payload) {
            // save sample
            await sleep(500)
            message.info('Successfully saved')
        },
        async deleteSample({ state, commit, dispatch }, payload) {
            try {
                commit('setDataLoading', true)
                await deleteSampleById(payload)
                message.info('Successfully deleted')
            } catch (err) {
                message.error(err.message)
            } finally {
                commit('setDataLoading', false)
            }
        }
    }
})
