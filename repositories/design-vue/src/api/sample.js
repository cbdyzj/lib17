import { sleep } from '@/utils/schedule'

/**
 * Search for sample list
 */
export async function searchSampleList(params) {
    return await mockSearchSampleList(params)
}

/**
 * Delete a sample
 */
export async function deleteSampleById(id) {
    return await mockDeleteSampleById(id)
}

const mockSampleList = Array.from({ length: 100 }).map((_, index) => {
    const num = index + 1
    return {
        key: String(num),
        id: String(num),
        name: `Sample ${num}`,
        type: Math.random() > 0.5 ? 'qualified' : 'unqualified',
    }
})

async function mockSearchSampleList(params) {
    await sleep(500)
    const { pageIndex, pageSize } = params
    let filteredList = [...mockSampleList]
    if (params.name && params.name.trim()) {
        filteredList = filteredList.filter(it => it.name.includes(params.name.trim()))
    }
    if (params.type) {
        filteredList = filteredList.filter(it => it.type === params.type)
    }
    const resultList = filteredList.slice((pageIndex - 1) * pageSize, (pageIndex - 1) * pageSize + pageSize)
    return {
        list: resultList,
        total: filteredList.length,
    }
}

async function mockDeleteSampleById(id) {
    await sleep(500)
    if(Math.random() < 0.5){
        throw new Error('The sample is in use and cannot be deleted')
    }

    const tempList = [...mockSampleList]
    mockSampleList.length = 0
    mockSampleList.push(...tempList.filter(it => it.id !== String(id)))
}