package com.ll.basic1.service;

import com.ll.basic1.model.Member;
import com.ll.basic1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    ArrayList<Member> arr = new ArrayList<>();

    public String add(Member member) {
        memberRepository.save(member);
        arr.add(member);
        return memberRepository.findById(member.getId()).get().getId() + "번 사람이 추가되었습니다.";
    }

    public String delete(Long id) {
        for (Member member : arr) {
            if (id == member.getId()) {
                arr.remove(member);
                return member.getId() + "번 사람이 삭제되었습니다.";
            }
        }
        return "그런 회원은 없습니다.";
    }

    public String modify(Long id, String name, Long age) {
        for (Member member : arr) {
            if (Objects.equals(id, member.getId())) {
                member.setName(name);
                member.setAge(age);
                return member.getId() + "번 사람이 수정되었습니다.";
            }
        }
        return "그런 회원은 없습니다.";
    }

    public List<Member> list() {
        memberRepository.findAll();
        return arr;
    }
}
